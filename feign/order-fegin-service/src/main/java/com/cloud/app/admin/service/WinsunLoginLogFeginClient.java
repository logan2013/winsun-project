package com.cloud.app.admin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.admin.fallback.WinsunLoginLogFeginClientFallback;


@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunLoginLogFeginClientFallback.class)
public interface WinsunLoginLogFeginClient {
	
	@RequestMapping(value = "/ciop/loginLog/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String beginTime, 
					   @RequestParam(required = false) String endTime, 
					   @RequestParam(required = false) String logName);
	
	@RequestMapping(value = "/ciop/loginLog/delLoginLog",method = RequestMethod.POST)
	public Object delLog();


}
