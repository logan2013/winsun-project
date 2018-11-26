package com.cloud.app.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.fallback.WinSunAdminFeginFallback;
import com.cloud.app.model.Dept;

@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinSunAdminFeginFallback.class)
public interface WinSunAdminDeptFeginClient {
	
	@RequestMapping(value = "/ciop/dept/tree",method = RequestMethod.GET)
	public Object tree();
	
	@RequestMapping(value = "/ciop/dept/dept_update/{deptId}",method = RequestMethod.GET)
    public Object deptUpdate(@PathVariable Integer deptId);
	
	@RequestMapping(value = "/ciop/dept/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> dept);
	
	@RequestMapping(value = "/ciop/dept/update",method = RequestMethod.POST)
	public Object update(@RequestParam Map<String,Object> dept);
	
	@RequestMapping(value = "/ciop/dept/delete",method = RequestMethod.POST)
	public Object delete(@RequestParam Integer deptId);

	@RequestMapping(value = "/ciop/dept/list",method = RequestMethod.GET)
	public Object list(String condition);
	
	@RequestMapping(value = "/ciop/dept/detail/{deptId}",method = RequestMethod.GET)
	public Object detail(@PathVariable("deptId") Integer deptId);
	
}
