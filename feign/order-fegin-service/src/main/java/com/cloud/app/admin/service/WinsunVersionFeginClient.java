package com.cloud.app.admin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.admin.fallback.WinsunVersionFeginClientFallback;


@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunVersionFeginClientFallback.class)
public interface WinsunVersionFeginClient {

	@RequestMapping("/ciop/version/update")
	public Object updateVersion(@RequestParam("version")String version,@RequestParam("softSystem") String softSystem);
	
}
