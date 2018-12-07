package com.cloud.app.admin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.admin.fallback.WinSunAdminFeginFallback;

@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinSunAdminFeginFallback.class)
public interface WinSunAdminFeginClient {
	
	@RequestMapping(value = "/ciop/login",method = RequestMethod.POST)
	public Object loginVali(@RequestParam("username")String username,@RequestParam("password")String password);
	
	@RequestMapping(value = "/ciop/acclogin",method = RequestMethod.POST)
	public Object accloginVali(@RequestParam("staffId")String subStaffId,@RequestParam(name = "staffPwd",required = false)String staffPwd,@RequestParam(name = "smsCode",required = false)String smsCode,@RequestParam(name = "theUuid",required = false)String theUuid);
	
	@RequestMapping(value = "/ciop/accSendSms", method = RequestMethod.POST)
    public Object accSendSms(@RequestParam("staffId")String subStaffId);
	
	@RequestMapping(value = "/ciop/logout")
    public Object logout();

}
