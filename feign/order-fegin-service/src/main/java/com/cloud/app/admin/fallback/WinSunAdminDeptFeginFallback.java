package com.cloud.app.admin.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.admin.service.WinSunAdminDeptFeginClient;
import com.cloud.app.admin.service.WinSunAdminFeginClient;
import com.cloud.app.model.Dept;

@Component
public class WinSunAdminDeptFeginFallback implements WinSunAdminDeptFeginClient{

	@Override
	public Object tree() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object deptUpdate(Integer deptId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object add(Map<String,Object> dept) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object update(Map<String,Object> dept) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object delete(Integer deptId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object list(String condition) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object detail(Integer deptId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

}
