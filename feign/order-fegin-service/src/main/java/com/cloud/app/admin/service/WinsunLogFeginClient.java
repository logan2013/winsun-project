package com.cloud.app.admin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.admin.fallback.WinsunKaptchaFeginClientFallback;
import com.cloud.app.admin.fallback.WinsunLogFeginClientFallback;

@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunLogFeginClientFallback.class)
public interface WinsunLogFeginClient {

	@RequestMapping(value = "/ciop/log/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String beginTime,
					   @RequestParam(required = false) String endTime,
					   @RequestParam(required = false) String logName,
					   @RequestParam(required = false) Integer logType);
	
	
	@RequestMapping(value = "/ciop/log/detail/{id}",method = RequestMethod.GET)
	public Object detail(@PathVariable Integer id);
	
	
	@RequestMapping(value = "/ciop/log/delLog",method = RequestMethod.POST)
	public Object delLog();
	
}
