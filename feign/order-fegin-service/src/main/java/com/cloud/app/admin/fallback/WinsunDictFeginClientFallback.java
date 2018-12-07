package com.cloud.app.admin.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.admin.service.WinsunDictFeginClient;

@Component
public class WinsunDictFeginClientFallback implements WinsunDictFeginClient{

	@Override
	public String index() {
		return "服务超时";
	}

	@Override
	public String deptAdd() {
		return "服务超时";
	}

	@Override
	public String deptUpdate(Integer dictId) {
		return "服务超时";
	}

	@Override
	public Object add(String dictCode, String dictTips, String dictName, String dictValues) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object list(String condition) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object detail(Integer dictId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object update(Integer dictId, String dictCode, String dictName, String dictTips, String dictValues) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object delete(Integer dictId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

}
