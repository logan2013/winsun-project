package com.cloud.app.admin.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.admin.fallback.WinsunRoleFeginClientFallback;
import com.cloud.app.model.Role;

@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunRoleFeginClientFallback.class)
public interface WinsunRoleFeginClient {
	
	@RequestMapping(value = "/ciop/role/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String roleName);
	
	@RequestMapping(value = "/ciop/role/role_info/{roleId}",method = RequestMethod.GET)
	public Object roleInfo(@PathVariable Integer roleId);
	
	@RequestMapping(value = "/ciop/role/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> role, @RequestParam("ids") String ids);
	
	@RequestMapping(value = "/ciop/role/edit",method = RequestMethod.POST)
	public Object edit(@RequestParam Map<String,Object> role, @RequestParam("ids") String ids);
	
	@RequestMapping(value = "/ciop/role/remove",method = RequestMethod.POST)
	public Object remove(@RequestParam Integer roleId);
	
	@RequestMapping(value = "/ciop/role/view/{roleId}",method = RequestMethod.GET)
	public Object view(@PathVariable Integer roleId);
	
	@RequestMapping(value = "/ciop/role/setAuthority",method = RequestMethod.POST)
	public Object setAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("ids") String ids);
	
	@RequestMapping(value = "/ciop/role/roleTreeList",method = RequestMethod.GET)
	public Object roleTreeList();
	
	@RequestMapping(value = "/ciop/role/roleTreeListByUserId/{userId}",method = RequestMethod.GET)
	public Object roleTreeListByUserId(@PathVariable Integer userId);

}
