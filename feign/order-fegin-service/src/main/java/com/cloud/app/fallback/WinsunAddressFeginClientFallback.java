package com.cloud.app.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.service.WinsunAddressFeginClient;

@Component
public class WinsunAddressFeginClientFallback implements WinsunAddressFeginClient{

	@Override
	public Object searchAddress(String substName, String address) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

}
