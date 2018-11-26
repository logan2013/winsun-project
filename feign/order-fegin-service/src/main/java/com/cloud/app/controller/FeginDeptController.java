package com.cloud.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.model.Dept;
import com.cloud.app.service.WinSunAdminDeptFeginClient;

@RestController
public class FeginDeptController {
	
	@Autowired
	WinSunAdminDeptFeginClient client;
	
	@RequestMapping(value = "/dept/tree",method = RequestMethod.GET)
	public Object tree() {
		return client.tree();
	}
	
	@RequestMapping(value = "/dept/dept_update/{deptId}",method = RequestMethod.GET)
	public Object dept_update(@PathVariable("deptId")Integer deptId) {
		return client.deptUpdate(deptId);
	}
	
	@RequestMapping(value = "/dept/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> dept) {
		return client.add(dept);
	}
	
	@RequestMapping(value = "/dept/update",method = RequestMethod.POST)
	public Object update(@RequestParam Map<String,Object> dept) {
		return client.update(dept);
	}
	
	@RequestMapping(value = "/dept/delete",method = RequestMethod.POST)
	public Object delete(@RequestParam Integer deptId) {
		return client.delete(deptId);
	}
	
	@RequestMapping(value = "/dept/list",method = RequestMethod.GET)
	public Object list(String condition) {
		return client.list(condition);
	}
	
	@RequestMapping(value = "/dept/detail/{deptId}",method = RequestMethod.GET)
	public Object detail(@PathVariable("deptId") Integer deptId) {
		return client.detail(deptId);
	}

}
