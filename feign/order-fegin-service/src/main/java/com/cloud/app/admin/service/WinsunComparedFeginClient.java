package com.cloud.app.admin.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.admin.fallback.WinSunAdminFeginFallback;
import com.cloud.app.admin.fallback.WinsunComparedFeginClientFallback;

@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunComparedFeginClientFallback.class)
public interface WinsunComparedFeginClient {
	
	@RequestMapping(value = "/ciop/compared/comparison",method = RequestMethod.POST)
	public Object compared(@RequestParam Map<String,Object> map);
	
	@RequestMapping(value = "/ciop/compared/test",method = RequestMethod.GET)
	public Object test();
	
	@RequestMapping(value = "/ciop/compared/agree",method = RequestMethod.POST)
	public Object agreeResult(@RequestParam("id")String id,@RequestParam("speed")String speed);
	
	@RequestMapping(value = "/ciop/compared/record/list",method = RequestMethod.POST)
	public Object recordList(@RequestParam Map<String,Object> map);
	
	@RequestMapping(value = "/ciop/compared/record/pc/list",method = RequestMethod.POST)
	public Object recordList2(@RequestParam Map<String,Object> map);
	
	@RequestMapping(value = "/ciop/compared/record/detail/{id}",method = RequestMethod.GET)
	public Object recordDetail(@PathVariable String id);

}
