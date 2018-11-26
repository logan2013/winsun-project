package com.cloud.app.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.model.Notice;
import com.cloud.app.service.WinsunNoticeFeginClient;

@Component
public class WinsunNoticeFeginClientFallback implements WinsunNoticeFeginClient{

	@Override
	public String noticeUpdate(Integer noticeId) {
		return "服务超时";
	}

	@Override
	public String hello() {
		return "服务超时";
	}

	@Override
	public Object list(String condition) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object add(Map<String,Object> notice) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object delete(Integer noticeId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object update(Map<String,Object> notice) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

}
