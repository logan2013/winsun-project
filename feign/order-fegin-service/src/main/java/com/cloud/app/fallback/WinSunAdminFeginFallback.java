package com.cloud.app.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.service.WinSunAdminFeginClient;

@Component
public class WinSunAdminFeginFallback implements WinSunAdminFeginClient{

	@Override
	public Object loginVali(String username, String password) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object logout() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	

}
