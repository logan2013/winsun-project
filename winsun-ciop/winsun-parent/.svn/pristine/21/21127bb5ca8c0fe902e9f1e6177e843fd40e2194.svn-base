package com.winsun.item.modular.compare.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.shiro.ShiroKit;
import com.winsun.item.core.shiro.ShiroUser;
import com.winsun.item.modular.compare.dao.ComparedRecordMapper;
import com.winsun.item.modular.compare.service.IComparedRecordService;
@Service
public class ComparedRecordServiceImpl implements IComparedRecordService{

	@Autowired
	private ComparedRecordMapper comparedRecordMapper;

	@Override
	public List<Map<String, Object>> selectList(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		List<Map<String, Object>> list = comparedRecordMapper.selectList(page, map);
		for(Map<String, Object> m : list){
			String param = (String) m.get("param");
			String best_meal = (String) m.get("best_meal");
			String recommend_meals = (String) m.get("recommend_meals");
			String account = (String) m.get("account");
			String create_time = m.get("create_time").toString();
			create_time = create_time.substring(0,10);
			JSONObject paramJson = JSONObject.fromObject(param);
			m.put("param", paramJson);
			m.put("best_meal", JSONObject.fromObject(best_meal));
			m.put("recommend_meals", JSONArray.fromObject(recommend_meals));
			m.put("account", JSONObject.fromObject(account));
			m.put("create_time", create_time);
			String customer_address = paramJson.getString("paramF");
			String customer_name = (String) paramJson.get("acc_name");
			m.put("customer_address", customer_address);
			m.put("customer_name", customer_name);
		}
		return list;
	}
	
	@Override
	public String addLogs(Map<String, Object> map, Map<String, Object> beatsMeal,
			List<Map<String, Object>> recommendMeals, Map<String, Object> accountMap, String status) {
		Map<String, Object> model = new HashMap<String, Object>();
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replaceAll("-", "");
		JSONObject param = JSONObject.fromObject(map);
		JSONObject beat_meal = JSONObject.fromObject(beatsMeal);
		JSONArray recommend_meals = JSONArray.fromObject(recommendMeals);
		JSONObject account = JSONObject.fromObject(accountMap);
		ShiroUser user = ShiroKit.getUser();
		
//		id, sale_name,user_id,user_account,param,best_meal,recommend_meals,create_time
		model.put("id", id);
		model.put("param", param.toString());
		model.put("best_meal", beat_meal.toString());
		model.put("recommend_meals", recommend_meals.toString());
		model.put("account", account.toString());
		model.put("sale_name", user.name);
		model.put("user_id", user.id);
		model.put("user_account", user.account);
		
		String customer_address = param.getString("paramF");
		String customer_name = (String) param.get("acc_name");
		model.put("customer_address", customer_address);
		model.put("customer_name", customer_name);
		
		System.err.println(model);
		int result = comparedRecordMapper.addLog(model);
		return id;
	}

	@Override
	public int updateRecordStatus(String id) {
		return comparedRecordMapper.updateRecordStatus(id);
	}

	@Override
	public List<Map<String, Object>> selectPcList(
			Page<Map<String, Object>> page, Map<String, Object> map) {
		List<Map<String, Object>> list = comparedRecordMapper.selectPcList(page, map);
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>(); 
		for(Map<String, Object> m : list){
			Map<String,Object> data = new HashMap<String,Object>();
			String id = (String) m.get("id");
			String param = (String) m.get("param");
			String best_meal = (String) m.get("best_meal");
			String recommend_meals = (String) m.get("recommend_meals");
			String account = (String) m.get("account");
			String create_time = m.get("create_time").toString();
			create_time = create_time.substring(0,10);
			data.putAll(JSONObject.fromObject(param));
			data.putAll(JSONObject.fromObject(best_meal));
			data.putAll(JSONObject.fromObject(account));
			data.put("create_time", create_time);
			data.put("recordId", id);
			dataList.add(data);
//			m.put("account", JSONObject.fromObject(account));
//			m.put("param", JSONObject.fromObject(param));
//			m.put("best_meal", JSONObject.fromObject(best_meal));
//			m.put("recommend_meals", JSONArray.fromObject(recommend_meals));
//			m.put("create_time", create_time);
		}
		return dataList;
	}

	@Override
	public Map<String, Object> selectById(String id) {
		Map<String, Object> map = comparedRecordMapper.selectById(id);
		if(map == null){
			return new HashMap();
		}
		id = (String) map.get("id");
		String param = (String) map.get("param");
		String best_meal = (String) map.get("best_meal");
		String account = (String) map.get("account");
		String recommend_meals = (String) map.get("recommend_meals");
		String create_time = map.get("create_time").toString();
		create_time = create_time.substring(0,10);
		map.put("recordId", id);
		map.put("account", JSONObject.fromObject(account));
		map.put("param", JSONObject.fromObject(param));
		map.put("best_meal", JSONObject.fromObject(best_meal));
		map.put("recommend_meals", JSONArray.fromObject(recommend_meals));
		map.put("create_time", create_time);
		return map;
	}

}
