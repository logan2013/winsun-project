package com.cloud.app.report.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.report.service.ReportBroadbandListFeginClient;

@Component
public class ReportBroadbandListFeginClientFallback implements ReportBroadbandListFeginClient{

	@Override
	public Object getDataList(Map<String, Object> dept) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object maxDate() {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

}