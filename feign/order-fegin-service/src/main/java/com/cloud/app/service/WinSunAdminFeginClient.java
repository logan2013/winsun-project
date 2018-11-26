package com.cloud.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.fallback.WinSunAdminFeginFallback;

@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinSunAdminFeginFallback.class)
public interface WinSunAdminFeginClient {
	
	@RequestMapping(value = "/ciop/login",method = RequestMethod.POST)
	public Object loginVali(@RequestParam("username")String username,@RequestParam("password")String password);
	
	@RequestMapping(value = "/ciop/logout")
    public Object logout();

}