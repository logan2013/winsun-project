package com.winsun.item.modular.compare.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.winsun.item.core.shiro.ShiroKit;
import com.winsun.item.core.shiro.ShiroUser;
import com.winsun.item.modular.compare.dao.ComparedMapper;
import com.winsun.item.modular.compare.dao.DimActDiscMapper;
import com.winsun.item.modular.compare.service.IComparedRecordService;
import com.winsun.item.modular.compare.service.IComparedService;
import com.winsun.item.modular.compare.util.CalPointInPoly;

@Service
public class ComparedServiceImpl implements IComparedService{
	@Autowired
	private DimActDiscMapper dimActDiscMapper;

	@Autowired
	private ComparedMapper comparedMapper;
	
	@Autowired
	private IComparedRecordService comparedRecordService;
	
	@Override
	public Map<String, Object> compare(Map<String, Object> map) {
		Map<String, Object> result = new HashMap();
		String paramAs = (String) map.get("paramAs");
		String paramBs = (String) map.get("paramBs");
		String paramCs = (String) map.get("paramC");
		String paramDs = (String) map.get("paramD");
		String paramEs = (String) map.get("paramEs");
		//消费数据录入
		Integer[] paramAa = spiltToInteger(paramAs);//{58,38,16,34,56,4};//手机月消费（参数A，必填）
		Integer[] paramBb = spiltToInteger(paramBs);//流量（参数B，必填）
		Integer paramC = Integer.parseInt(paramCs);//0;//宽带月消费（参数C，必填）
		Integer paramD = Integer.parseInt(paramDs);;//宽带速率（参数D，必填）
		Integer[] paramEa = spiltToInteger(paramEs);//{12,35};//电视月消费（参数E，非必填）
		String paramF = (String) map.get("paramF");//“住址”（参数F，必填，长度不限）
		
		String latStr = (String) map.get("lat");
		String lngStr = (String) map.get("lng");
		double lat = 0.0;
		double lng = 0.0;
		if(!"undefined".equals(latStr) && !"undefined".equals(latStr) ){
			lat = Double.parseDouble(latStr);
			lng = Double.parseDouble(lngStr);
		}
		
		Map<String, Object> gridMap = addressToGrid(paramF);//地址转换网格编码
		//“推荐套餐”展示板块
		String gridCode = (String) gridMap.get("gridCode");
		
		if(StringUtils.isBlank(gridCode) && !"undefined".equals(latStr) && !"undefined".equals(latStr) ){
			gridMap = getGridCodeByLocation(lat,lng,paramF);
			gridCode = (String) gridMap.get("gridCode");
		}
		if(StringUtils.isBlank(gridCode)){
			result.put("msg", "亲，该地址无法搜索套餐，请重新输入地址！");
			return result;
		}
		String paramH = gridCodeToProperty(gridCode);//网格属性,数值有5类：普通区域、高流失区域、高竞争区域、农村区域、城中村区域
		//“原消费”展示板块
		Integer paramA = 0;
		if(paramAa.length > 0 ){
			for(Integer a : paramAa){
				paramA += a;
			}
		}
		
		Integer paramE = 0;
		if(paramEa.length > 0){
			for(Integer e : paramEa){
				paramE += e;
			}			
		}
		
		Integer paramS = paramA + paramC + paramE;//原总消费
		
		//List<Map<String, Object>> meals = getMealsByCodeAndProperty(gridCode, paramH);//获取候选套餐
		//更多
		List<Map<String, Object>> recommendedMeals = new ArrayList<>();//符合规则的插入
		//最佳
		Map<String, Object> beatsMeal = new HashMap<>();
		//String sql = "SELECT * FROM dim_act_disc where region_type='"+paramH+"'order by disc_value desc";
		Map<String, Object> paramMap = new HashMap();
		paramMap.put("regionType", paramH);
		List<Map<String, Object>> meals = dimActDiscMapper.selectListByMap(paramMap);
		//遍历候选套餐比较
		//if(meals.size() == 0)
			//return;
		boolean hasBeat = false;
		for(Map<String,Object> meal : meals){
			
			String paramIs = meal.get("disc_value").toString();//“套餐档次”（参数值I）
			String paramJs =  meal.get("speed_value").toString();//“宽带速率”（参数值J）
			Integer paramI = Integer.parseInt(paramIs);
			Integer paramJ = Integer.parseInt(paramJs);
			//当用户输入手机个数大于3时，即A4/A5大于0，每增加1张卡，参数I需+10，最多+20。
			//手机个数大于五的只取到5
			int phoneSize = paramAa.length > 5 ? 5 : paramAa.length;
			if(phoneSize > 3){
				paramI += 10 * (phoneSize-3);
				//推荐套餐的总费用
				meal.put("disc_value", paramI);
			}
			//每月省（参数X）元：X=|S-I|
			Integer SaveMonthX = paramS - paramI;
			meal.put("SaveMonthX", SaveMonthX);
			//每年省（参数Y）元：Y=X*12
			Integer SaveYearY = SaveMonthX * 12;
			meal.put("SaveYearY", SaveYearY);
			//天翼高清参数
			String itvValue = meal.get("itv_value").toString().equals("1") ? "4K电视机顶盒" : "";
			int itv = meal.get("itv_value").toString().equals("1")?1:0;
			// 取值也是这个key
			meal.put("itv_value", itvValue);
			
			//比算规则1：若D≤J且S≥I，则在“推荐套餐”板块，输出符合规则的“套餐档次”最高（即参数I最大）的相关参数；
			//比算规则2：若比算规则1运算结果为NULL，则执行比算规则2，即若S≥I,则在“推荐套餐”板块，输出符合规则的“套餐档次”最高（即参数I最大）的相关参数；
			//比算规则3：若S<I，则在“推荐套餐”板块，输出符合规则的“套餐档次”最低的相关参数。
			//其他符合规则的“套餐档次”，可通过点击“更多”按钮查看。
			if(paramD <=  paramJ && paramS >= paramI){
				if(!hasBeat){
					beatsMeal = meal;
					hasBeat = true;
				}else {
					recommendedMeals.add(meal);
				}
			}else if(paramS >= paramI){
				if(!hasBeat){
					beatsMeal = meal;
					hasBeat = true;
				}else {
					recommendedMeals.add(meal);
				}
			}else if(paramS < paramI){
				if(!hasBeat){
					beatsMeal = meal;
				}
			}
			
			meal.put("paramH", paramH);
			meal.put("paramI", paramI);
			meal.put("paramJ", paramJ);
			meal.put("paramK", meal.get("mou_call"));
			meal.put("paramL", meal.get("stm_data_value"));
			meal.put("paramLStr", meal.get("stm_data"));
			meal.put("paramMStr", itvValue);
			meal.put("paramM", itv);
			meal.put("paramS", paramS);
			meal.put("paramX", SaveMonthX);
			meal.put("paramY", SaveYearY);
		}
		result.put("recommendedMeals", recommendedMeals);
		result.put("beatsMeal", beatsMeal);
		
		ShiroUser user = ShiroKit.getUser();
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", user.getName());
		m.put("phone", user.getPhone());
		m.put("recommendDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		m.put("remark", map.get("remark"));
		result.put("account", m);
		result.put("param", map);
		
		//比算日志记录
		String rId = comparedRecordService.addLogs(map,beatsMeal,recommendedMeals, m, "0");
		result.put("recordId", rId);
		System.err.println(result);
		return result;
	}
	
	/**
	 * 地址转换网格编码
	 * @param address
	 * @return
	 */
	public Map<String, Object> addressToGrid(String address){
		String gridCode = comparedMapper.getGridCodeByAddr(address);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("gridCode", gridCode);
		return map;
	}
	
	/**
	 * 根据网格编码返回对应区域标签
	 * @param gridCode
	 * @return
	 */
	public String gridCodeToProperty(String gridCode){
		String gridLabel = comparedMapper.getGridCodeByAddr(gridCode);
		if(StringUtils.isNoneBlank(gridLabel)){
			return gridLabel;			
		}
		String[] propertyArr = {"普通区域","高流失区域","高竞争区域","农村区域","城中村区域"};
		return propertyArr[(int) (Math.floor(Math.random()*5)%5)];
	} 
	
	public Integer[] spiltToInteger(String str){
		if(StringUtils.isBlank(str)){
			Integer[] arr = {0};
			return arr;
		}
		String[] paramStr = str.split(",");
		Integer[] arr = new Integer[paramStr.length];
		int i = 0;
		for(String s : paramStr){
			arr[i++] = Integer.parseInt(s);
		}
		return arr;
	}
	
	/**
	 * 根据地址以及经纬度获取所处网格编码
	 * Test
	 * @param lat
	 * @param lng
	 * @return
	 */
	public String getGridByPoint(String address, String lat, String lng){
		try {
			String[] substs = {"天河","白云","越秀","黄埔","东山","海珠","荔湾","花都","增城","从化","番禺","南沙"};
			String subst = "";
			for(String s : substs){
				if(address.indexOf(s) > -1){
					subst = s;
					break;
				}
			}
			if(StringUtils.isBlank(subst)){
				return "";
			}
			InputStream stream = getClass()
					.getClassLoader()
					.getResourceAsStream("json/" + subst + ".json"); 
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			String line = null;
			StringBuilder sBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sBuilder.append(line);
			}
			JSONArray json = JSONArray.fromObject(sBuilder.toString());
			System.err.println(json.size());
			br.close();
			stream.close();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "";
	}

	/**
	 * 根据经纬度查询出地理位置
	 * @param lat
	 * @param lng
	 * @param addresss
	 * @return
	 */
	public Map<String, Object> getGridCodeByLocation(double lat,double lng,String addresss){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String,Object>> list2 = initGridData(addresss);
		Map<String,Double[][]> list2Map = new HashMap<>();
		for (int j = 0; j < list2.size(); j++) {
			Map<String,Object> data2 = list2.get(j);
			if(data2==null){
				continue;
			}
			String subst_name = (String) data2.get("subst_name");
			String branch_name = (String) data2.get("branch_name");
			String grid_name = (String) data2.get("grid_name");
			String grid_code = (String) data2.get("grid_code");
			String point = (String) data2.get("point");
			if(point==null || point.isEmpty()){
				continue;
			}
			String[] points = point.split("#");
			boolean zjjg = true;//最终结果
			for (int k = 0; k < points.length; k++) {//遍历每个多边形
				if(points[k]==null || points[k].isEmpty()){
					continue;
				}
				Double[][] result = null;
				if(list2Map.get(grid_name) != null){//字典法
					result = list2Map.get(grid_name+k);
				}else{
					result = CalPointInPoly.calPiontXY(points[k]);
					list2Map.put(grid_name+k,result);
				}
				int amount = CalPointInPoly.calPiontAmount(points[k]);
				boolean rs = CalPointInPoly.pnpoly(amount, result[0], result[1], lng, lat);
				if(k == 0){
					if(!rs){
						zjjg = false;
						break;
					}
				}else{
					if(rs){
						zjjg = false;
						break;
					}
				}
			}
			if(zjjg){//最终结果true,匹配成功
				map.put("gridName", grid_name);
				map.put("gridCode", grid_code);
			}
		}
		return map;
	}
	
	@Override
	public Map<String, Object> test() {
		long start = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		double lat = 23.10871181423915;
		double lng = 113.49288457424916;
		List<Map<String,Object>> list2 = initGridData("广东省广州市越秀区忠佑大街");
		System.out.println(list2.size());
		System.out.println(System.currentTimeMillis()-start);
		Map<String,Double[][]> list2Map = new HashMap<>();
		for (int j = 0; j < list2.size(); j++) {
			Map<String,Object> data2 = list2.get(j);
			if(data2==null){
				continue;
			}
//			System.out.println("data2====="+data2);
			String subst_name = (String) data2.get("subst_name");
			String branch_name = (String) data2.get("branch_name");
			String grid_name = (String) data2.get("grid_name");
			String grid_code = (String) data2.get("grid_code");
			String point = (String) data2.get("point");
			if(point==null || point.isEmpty()){
				continue;
			}
//			System.out.println("point====="+point);
			String[] points = point.split("#");
			boolean zjjg = true;//最终结果
			for (int k = 0; k < points.length; k++) {//遍历每个多边形
				if(points[k]==null || points[k].isEmpty()){
					continue;
				}
				Double[][] result = null;
				if(list2Map.get(grid_name) != null){//字典法
					result = list2Map.get(grid_name+k);
				}else{
					result = CalPointInPoly.calPiontXY(points[k]);
					list2Map.put(grid_name+k,result);
				}
				int amount = CalPointInPoly.calPiontAmount(points[k]);
//				System.out.println(grid_name+k+":"+amount);
//				System.out.println("lng: "+lng+"，lng: "+lng+"，nums: "+CalPointInPoly.calPiontAmount(points[k])+"，result: "+result.toString());
				boolean rs = CalPointInPoly.pnpoly(amount, result[0], result[1], lng, lat);
//				System.out.println("rs: "+rs);
				if(k == 0){
					if(!rs){
						zjjg = false;
						break;
					}
				}else{
					if(rs){
						zjjg = false;
						break;
					}
				}
			}
			if(zjjg){//最终结果true,匹配成功
				map.put("gridName", grid_name);
				map.put("gridCode", grid_code);
			}
//			System.out.println(System.currentTimeMillis()-start);
		}
		System.out.println(System.currentTimeMillis()-start);
		return map;
	}
	
	public List initGridData(String address){
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			String[] substArr = {"天河","白云","越秀","黄埔","东山","海珠","荔湾","花都","增城","从化","番禺","南沙"};
//			String[] substs = {"越秀"};
			String[] substs = substArr;
			for(String str : substArr){
				if(address.indexOf(str) > -1){
					substs = new String[]{str};
				}
			}
			
			for(String subst:substs){
				String path = "gridJson/" + subst + ".json";
				ClassPathResource classPathResource = new ClassPathResource(path);
				System.out.println(subst);
				InputStream input = null;
				InputStreamReader inp = null;
				BufferedReader reader = null;
				long start = System.currentTimeMillis();
				input = classPathResource.getInputStream();
				inp = new InputStreamReader(input, "UTF-8");// 字节流字符流转化的桥梁
				reader = new BufferedReader(inp);// 以字符流方式读入
				String line = null;
				StringBuilder sBuilder = new StringBuilder();
				while ((line = reader.readLine()) != null) {
					sBuilder.append(line.replaceAll(" ", ""));
				}
				long end = System.currentTimeMillis();
				System.out.println("deta1:"+(end-start));
				JSONArray json = JSONArray.fromObject(sBuilder.toString());//后续使用缓存，启动时将数据缓存起来
				list.addAll(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
