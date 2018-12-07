package com.cloud.app.admin.fallback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.admin.service.TokenFeginClient;


@Component
public class TokenFeginClientFllBack implements TokenFeginClient{

	
//	@Override
//	public Object getTokenForClient(String type, String scope, String client_id, String client_secret) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("500", "服务异常");
//		return new JSONObject(map);
//	}
//
//	@Override
//	public Object getTokenForPassWord(String type, String username, String password, String scope, String client_id,
//			String client_secret) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("500", "服务异常");
//		return new JSONObject(map);
//	}
//
//	@Override
//	public Object queryUser(String username) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("500", "服务异常");
//		return new JSONObject(map);
//	}


}
