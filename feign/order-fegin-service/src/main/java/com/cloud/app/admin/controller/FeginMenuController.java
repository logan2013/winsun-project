package com.cloud.app.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.admin.service.WinsunMenuFeginClient;
import com.cloud.app.model.Menu;

@RestController
public class FeginMenuController {
	
	@Autowired
	WinsunMenuFeginClient client;
	
	@RequestMapping(value = "/menu/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String menuName, @RequestParam(required = false) String level) {
		return client.list(menuName, level);
	}
	
	@RequestMapping(value = "/menu/menu_info/{menuId}",method = RequestMethod.GET)
	public Object menuInfo(@PathVariable Long menuId) {
		return client.menuInfo(menuId);
	}
	
	@RequestMapping(value = "/menu/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> menu) {
		return client.add(menu);
	}
	
	@RequestMapping(value = "/menu/edit",method = RequestMethod.POST)
	public Object edit(@RequestParam Map<String,Object> menu) {
		return client.edit(menu);
	}
	
	@RequestMapping(value = "/menu/remove",method = RequestMethod.POST)
	public Object remove(@RequestParam Long menuId) {
		return client.remove(menuId);
	}
	
	@RequestMapping(value = "/menu/view/{menuId}",method = RequestMethod.GET)
	public Object view(@PathVariable Long menuId) {
		return client.view(menuId);
	}
	
	@RequestMapping(value = "/menu/menuTreeList",method = RequestMethod.GET)
	public Object menuTreeList() {
		return client.menuTreeList();
	}
	
	@RequestMapping(value = "/menu/selectMenuTreeList",method = RequestMethod.GET)
	public Object selectMenuTreeList() {
		return client.selectMenuTreeList();
	}
	
	@RequestMapping(value = "/menu/menuTreeListByRoleId/{roleId}",method = RequestMethod.GET)
	public Object menuTreeListByRoleId(@PathVariable Integer roleId) {
		return client.menuTreeListByRoleId(roleId);
	}

	@RequestMapping(value = "/menu/getMenus",method = RequestMethod.GET)
	public Object getMenus() {
		return client.getMenus();
	}
	
	@RequestMapping(value = "/menu/freeze",method = RequestMethod.POST)
	public Object freeze(@RequestParam Long menuId) {
		return client.freeze(menuId);
	}
	
	@RequestMapping(value = "/menu/unfreeze",method = RequestMethod.POST)
	public Object unfreeze(@RequestParam Long menuId) {
		return client.unfreeze(menuId);
	}

}
