package com.cloud.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cloud.app.service.TokenFeginClient;
import com.cloud.app.service.WinSunAdminFeginClient;




@RestController
public class FeginLoginController {
	
	
	@Autowired
	WinSunAdminFeginClient client;

	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public Object login(@RequestParam("username")String username,@RequestParam("password")String password,HttpServletRequest request) {
		return client.loginVali(username, password);
	}
	
	@RequestMapping(value = "/logout")
	public Object logout() {
		return client.logout();
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@RequestMapping(value = "/getToken",method = RequestMethod.POST)
//	public JSONObject getToken(@RequestParam("type") String type,
//            @RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest request) {
//		
//		threadLocal.set(request);
//		
//		JSONObject json = null;
//		
//		if(type == null || "".equals(type) || username == null || password == null) {
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("400", "参数不完整");
//			json = new JSONObject(map);
//			return json;
//		}
//		
//		if("password".equals(type)) {
//			Object obj = tclient.getTokenForPassWord(type, username, password, "select", "client_2", "123456");
//			json = (JSONObject) JSONObject.toJSON(obj);
//		}
//		else if("client_credentials".equals(type)) {
//			Object obj = tclient.getTokenForClient(type, "select", "client_1", "123456");
//			json = (JSONObject) JSONObject.toJSON(obj);
//		}else {
//			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("500", "类型错误");
//			json = new JSONObject(map);
//		}
//		
////		Object obj = tclient.queryUser(username);
////		json = (JSONObject) JSONObject.toJSON(obj);
//		return json;
//		
//	}

	
}
