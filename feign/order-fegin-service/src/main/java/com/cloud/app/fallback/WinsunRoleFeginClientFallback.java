package com.cloud.app.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.model.Role;
import com.cloud.app.service.WinsunRoleFeginClient;

@Component
public class WinsunRoleFeginClientFallback implements WinsunRoleFeginClient{

	@Override
	public Object list(String roleName) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object roleInfo(Integer roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object add(Map<String,Object> role, String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object edit(Map<String,Object> role, String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object remove(Integer roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object view(Integer roleId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object setAuthority(Integer roleId, String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object roleTreeList() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

	@Override
	public Object roleTreeListByUserId(Integer userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("500", "服务超时");
		JSONObject json = new JSONObject(map);
		return json;
	}

}
