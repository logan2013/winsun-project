package com.cloud.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.model.Role;
import com.cloud.app.service.WinsunRoleFeginClient;

@RestController
public class FeginRoleController {
	
	@Autowired
	WinsunRoleFeginClient client;
	
	@RequestMapping(value = "/role/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String roleName) {
		return client.list(roleName);
	}
	
	@RequestMapping(value = "/role/role_info/{roleId}",method = RequestMethod.GET)
	public Object roleInfo(@PathVariable Integer roleId) {
		return client.roleInfo(roleId);
	}
	
	@RequestMapping(value = "/role/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> role, @RequestParam("ids") String ids) {
		return client.add(role, ids);
	}
	
	@RequestMapping(value = "/role/edit",method = RequestMethod.POST)
	public Object edit(@RequestParam Map<String,Object> role, @RequestParam("ids") String ids) {
		return client.edit(role, ids);
	}
	
	@RequestMapping(value = "/role/remove",method = RequestMethod.POST)
	public Object remove(@RequestParam Integer roleId) {
		return client.remove(roleId);
	}
	
	@RequestMapping(value = "/role/view/{roleId}",method = RequestMethod.GET)
	public Object view(@PathVariable Integer roleId) {
		return client.view(roleId);
	}
	
	@RequestMapping(value = "/role/setAuthority",method = RequestMethod.POST)
	public Object setAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("ids") String ids) {
		return client.setAuthority(roleId, ids);
	}
	
	@RequestMapping(value = "/role/roleTreeList",method = RequestMethod.GET)
	public Object roleTreeList() {
		return client.roleTreeList();
	}
	
	@RequestMapping(value = "/role/roleTreeListByUserId/{userId}",method = RequestMethod.GET)
	public Object roleTreeListByUserId(@PathVariable Integer userId) {
		return client.roleTreeListByUserId(userId);
	}

}
