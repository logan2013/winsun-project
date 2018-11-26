package com.cloud.app.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.model.User;
import com.cloud.app.service.WinsunUserMgrFeginClient;

@Component
public class WinsunUserMgrFeginClientFallback implements WinsunUserMgrFeginClient{

	@Override
	public Object list(String name, String beginTime, String endTime, Integer deptid) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object userEdit(Integer userId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object add(Map<String,Object> user) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object edit(Map<String,Object> user) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object freeze(Integer userId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object unfreeze(Integer userId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object delete(Integer userId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object reset(Integer userId) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object changePwd(String oldPwd, String newPwd, String rePwd) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}

	@Override
	public Object setRole(Integer userId, String roleIds) {
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("500", "服务超时");
		JSONObject json = new JSONObject(returnMap);
		return json;
	}



}
