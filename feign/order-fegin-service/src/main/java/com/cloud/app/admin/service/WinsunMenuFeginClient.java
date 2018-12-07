package com.cloud.app.admin.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.admin.fallback.WinsunMenuFeginClientFallback;
import com.cloud.app.model.Menu;


@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunMenuFeginClientFallback.class)
public interface WinsunMenuFeginClient {
	
	@RequestMapping(value = "/ciop/menu/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String menuName, @RequestParam(required = false) String level);
	
	@RequestMapping(value = "/ciop/menu/menu_info/{menuId}",method = RequestMethod.GET)
	public Object menuInfo(@PathVariable Long menuId);
	
	@RequestMapping(value = "/ciop/menu/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> menu);
	
	@RequestMapping(value = "/ciop/menu/edit",method = RequestMethod.POST)
	public Object edit(@RequestParam Map<String,Object> menu);
	
	@RequestMapping(value = "/ciop/menu/remove",method = RequestMethod.POST)
	public Object remove(@RequestParam Long menuId);
	
	@RequestMapping(value = "/ciop/menu/view/{menuId}",method = RequestMethod.GET)
	public Object view(@PathVariable Long menuId);
	
	@RequestMapping(value = "/ciop/menu/menuTreeList",method = RequestMethod.GET)
	public Object menuTreeList();
	
	@RequestMapping(value = "/ciop/menu/selectMenuTreeList",method = RequestMethod.GET)
	public Object selectMenuTreeList();
	
	@RequestMapping(value = "/ciop/menu/menuTreeListByRoleId/{roleId}",method = RequestMethod.GET)
	public Object menuTreeListByRoleId(@PathVariable Integer roleId);

	@RequestMapping(value = "/ciop/menu/getMenus",method = RequestMethod.GET)
	public Object getMenus();
	
	@RequestMapping(value = "/ciop/menu/freeze",method = RequestMethod.POST)
	public Object freeze(@RequestParam Long menuId);
	
	@RequestMapping(value = "/ciop/menu/unfreeze",method = RequestMethod.POST)
	public Object unfreeze(@RequestParam Long menuId);
}
