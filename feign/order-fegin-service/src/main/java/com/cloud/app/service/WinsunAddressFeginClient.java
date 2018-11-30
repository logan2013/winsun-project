package com.cloud.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.fallback.WinSunAdminFeginFallback;
import com.cloud.app.fallback.WinsunAddressFeginClientFallback;

@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunAddressFeginClientFallback.class)
public interface WinsunAddressFeginClient {
	
	@RequestMapping("/ciop/address/search")
	public Object searchAddress(@RequestParam("substName")String substName,@RequestParam("address") String address);

}
