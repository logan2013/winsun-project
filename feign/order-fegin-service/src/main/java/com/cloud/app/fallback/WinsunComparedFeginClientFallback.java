package com.cloud.app.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.service.WinsunComparedFeginClient;

@Component
public class WinsunComparedFeginClientFallback implements WinsunComparedFeginClient{

	@Override
	public Object compared(Map<String, Object> map) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object test() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object agreeResult(String id,String speed) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object recordList(Map<String,Object> map) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object recordList2(Map<String, Object> map) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object recordDetail(String id) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

}
