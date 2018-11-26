package com.cloud.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.app.fallback.TokenFeginClientFllBack;

//@FeignClient(value = "winsun-oauth-service",fallback = TokenFeginClientFllBack.class)
public interface TokenFeginClient {
	
//	@RequestMapping(value = "/oauth/token",method = RequestMethod.POST)
//	Object getTokenForPassWord(@RequestParam("grant_type") String type,
//            @RequestParam("username") String username, @RequestParam("password") String password,
//            @RequestParam("scope") String scope, @RequestParam("client_id") String client_id,@RequestParam("client_secret") String client_secret);
//	
//	@RequestMapping(value = "/oauth/token",method = RequestMethod.POST)
//	Object getTokenForClient(@RequestParam("grant_type") String type,
//            @RequestParam("scope") String scope, @RequestParam("client_id") String client_id,@RequestParam("client_secret") String client_secret);
//	
//	@RequestMapping(value = "/query/user",method = RequestMethod.GET)
//	Object queryUser(@RequestParam("username")String username);


}
