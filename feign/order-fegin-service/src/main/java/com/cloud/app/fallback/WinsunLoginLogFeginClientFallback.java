package com.cloud.app.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.service.WinsunLoginLogFeginClient;

@Component
public class WinsunLoginLogFeginClientFallback implements WinsunLoginLogFeginClient{

	@Override
	public Object list(String beginTime, String endTime, String logName) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object delLog() {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

}
