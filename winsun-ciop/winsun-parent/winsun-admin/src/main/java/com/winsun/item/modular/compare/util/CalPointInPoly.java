package com.winsun.item.modular.compare.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 判断点是否在多边形中
 * @author 吕伟康
 * @date 2018年2月8日下午6:44:55
 */
public class CalPointInPoly {
	
	/**
	 * 匹配上商圈与网点
	 * 
	 * @return
	 */
	public Object calBusDists(List<Map<String, Object>> busList, List<Map<String, Object>> locList) {
//		===============busList=================
//		{id=27, name=从化新城东路商圈, path=113.591832,23.554047;113.594203,23.554047;113.59891,23.551231;113.598659,23.549939;113.599126,23.547189;113.590466,23.546924, dist_level=二类商圈, subst_name=从化, dist_nbr=S44018400003, dist_type=商场购物类, dist_area=府前路以东，新城东路以西，中田东路以北的区域}
//		===============locList=================
//		{crm_code=GZTY3GTH00287, sales_name=奕馨通讯天翼合作店, baidu=113.334447,23.177161, loc_grid=}
//		=================After=================
//		{crm_code=GZTY3GTH00287, sales_name=奕馨通讯天翼合作店, baidu=113.334447,23.177161, loc_grid=XXXXX商圈}
		List list = cal(busList, locList);
		// System.out.println("=================After=================");
		// for(Object obj:locList)
		// System.out.println(obj);
		return locList;
	}
	
	/**
	 * 计算网点在那个商圈多边形中
	 * 
	 * @param busList
	 * @param locList
	 */
	public static List cal(List<Map<String, Object>> busList, List<Map<String, Object>> locList) {
		List list = new ArrayList<>();
		List<Map<String, Object>> newBusList = new ArrayList<Map<String, Object>>(busList.size());
		for (Map<String, Object> data : busList) {// 分离各个多边形的点
			String path = (String) data.get("path");
			long id = (Long) data.get("id");
			String name = (String) data.get("name");
			int amount = calPiontAmount(path);
			Map<String, Object> busMap = splitPoint(path, amount);
			busMap.put("id", id);
			busMap.put("amount", amount);
			busMap.put("name", name);
			newBusList.add(busMap);
			// System.out.println(busMap);
		}

		Iterator<Map<String, Object>> iterator = locList.iterator();
		for (; iterator.hasNext();) {
			Map<String, Object> data = iterator.next();
			String baidu = (String) data.get("baidu");
			if (StringUtils.isBlank(baidu))
				continue;
			String[] str = baidu.split(",");
			double x = Double.parseDouble(str[0]);
			double y = Double.parseDouble(str[1]);
			Iterator<Map<String, Object>> it = newBusList.iterator();
			for (; it.hasNext();) {
				Map<String, Object> detail = it.next();
				int amount = (Integer) detail.get("amount");
				Double[] xx = (Double[]) detail.get("x");
				Double[] yy = (Double[]) detail.get("y");
				if (pnpoly(amount, xx, yy, x, y)) {
					String name = (String) detail.get("name");
					data.put("loc_grid", name);
					list.add(data);
					break;
				}
			}
		}
		return list;
	}

	/**
	 * 字符串分割
	 * @param str
	 * @return
	 */
	public static Double[][] calPiontXY(String str) {
		String[] attr = str.split(";");
		Double[] x = {};
		Double[] y = {};
		List<Double> lx = new ArrayList<>();
		List<Double> ly = new ArrayList<>();
		for (int i = 0; i < attr.length; i++) {
			if(attr[i].indexOf(",")==-1){
				continue;
			}
			String[] xy = attr[i].split(",");
			if(xy.length!=2){
				continue;
			}
			lx.add(Double.valueOf(xy[0].toString().trim()));
			ly.add(Double.valueOf(xy[1].toString().trim()));
		}
		x = lx.toArray(new Double[lx.size()]);
		y = ly.toArray(new Double[ly.size()]);
		Double[][] result = new Double[][]{x,y};
		return result;
	}

	
	/**
	 * 计算多边形点的个数
	 * 
	 * @param str
	 * @return
	 */
	public static int calPiontAmount(String str) {
		// String str =
		// "113.314531,23.101639;113.318609,23.101921;113.318645,23.101589;
		// 113.31613,23.101406;113.314531,23.101273";
		String des = ";";
		int cnt = 0;
		int offset = 0;
		while ((offset = str.indexOf(des, offset)) != -1) {
			offset = offset + des.length();
			cnt++;
		}
		return cnt;
	}

	/**
	 * 分离商圈的点
	 * 
	 * @param path
	 * @param size
	 * @return
	 */
	public static Map<String, Object> splitPoint(String path, int size) {
		Map<String, Object> map = new HashMap<String, Object>();
		Double x[] = new Double[size];
		Double y[] = new Double[size];
		String[] str = path.split(";");
		int i = 0;
		for (String st : str) {
			String[] s = st.split(",");
			x[i] = Double.parseDouble(s[0]);
			y[i] = Double.parseDouble(s[1]);
			i++;
		}
		// System.out.print(Arrays.asList(x)+":");
		// System.out.println(Arrays.asList(y));
		map.put("x", x);
		map.put("y", y);
		return map;
	}
	
	/**
	 * 判断一个坐标点是否在多边形内 算法原理：一个点发出一条射线，记录交多边形的交点， 若交点个数为奇数则在多边形内，若为偶数则在多边形外
	 * 不过要处理特殊情况
	 * 
	 * @param amount
	 *            多边形点的个数
	 * @param vertX
	 *            x坐标的集合
	 * @param vertY
	 *            y坐标的集合
	 * @param pointX
	 *            测试点x坐标
	 * @param pointY
	 *            测试点y坐标
	 * @return
	 */
	public static boolean pnpoly(int amount, Double[] vertX, Double[] vertY, double pointX, double pointY) {

//		System.out.println(Arrays.asList(vertX));
//		System.out.println(Arrays.asList(vertY));
		
		// 获取多边形所在的最小内接四边形的坐标
		double minX = vertX[0];
		double maxX = vertX[0];
		double minY = vertY[0];
		double maxY = vertY[0];
		for (double d : vertX) {
			if (minX > d)
				minX = d;
			if (maxX < d)
				maxX = d;
		}
		for (double d : vertY) {
			if (minY > d)
				minY = d;
			if (maxY < d)
				maxY = d;
		}

		// 不在最小四边形内肯定不在多边形中
		if (pointX < minX || pointX > maxX || pointY < minY || pointY > maxY) {
			return false;
		}

		int i, j;
		boolean flag = false;
//		for (i = 0, j = amount - 1; i < amount; j = i++) {
//			double sx = vertX[i], sy = vertY[i], tx = vertX[j], ty = vertY[j];
//			if (((sy > pointY) != (ty > pointY)) && (pointX < (tx - sx) * (pointY - sy) / (ty - sy) + sx)) {
//				// return true;
//				flag = !flag;
//			}
//		}
		// 根据特殊情况进行优化
		for (i = 0, j = amount - 1; i < amount; j = i++) {
			double sx = vertX[i], sy = vertY[i], tx = vertX[j], ty = vertY[j];
			// 特殊情况：
			// 1.点与多边形顶点重合
			if ((sx == pointX && sy == pointY) || (tx == pointX && ty == pointY)) {
				return true;
			}
			// 判断线段两端点是否在射线两侧
			if ((sy < pointY && ty >= pointY) || (sy >= pointY && ty < pointY)) {
				// 线段上与射线 Y 坐标相同的点的 X 坐标
				double x = sx + (pointY - sy) * (tx - sx) / (ty - sy);
				// 2.点在多边形的边上
				if (x == pointX) {
					return true;
				}
				// 射线穿过多边形的边界
				if (x > pointX) {
					flag = !flag;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 判断一个坐标点是否在多边形附近
	 * 
	 * @param nvert
	 *            多边形点的个数
	 * @param vertx
	 *            x坐标的集合
	 * @param verty
	 *            y坐标的集合
	 * @param testx
	 *            测试点x坐标
	 * @param testy
	 *            测试点y坐标
	 * @return
	 */
	public static boolean  pnpolyNear(int nvert, Double[] vertx, Double[] verty, double testx, double testy) {
		boolean flag = false;
		double minX = vertx[0];
		double maxX = vertx[0];
		double minY = verty[0];
		double maxY = verty[0];

		for (double d : vertx) {
			if (minX > d)
				minX = d;
			if (maxX < d)
				maxX = d;
		}
		for (double d : verty) {
			if (minY > d)
				minY = d;
			if (maxY < d)
				maxY = d;
		}
		minX = (minX * 1000000 - 3000) / 1000000;
		maxX = (maxX * 1000000 + 3000) / 1000000;
		minY = (minY * 1000000 - 3000) / 1000000;
		maxY = (maxY * 1000000 + 3000) / 1000000;
		if (testx >= minX && testx <= maxX && testy >= minY && testy <= maxY) {
			flag = true;
		}

		return flag;
	}
}
