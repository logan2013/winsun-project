	package com.cloud.app.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.app.admin.service.WinsunUploadFeginClient;
import com.cloud.app.admin.service.WinsunUserMgrFeginClient;
import com.cloud.app.model.User;

@RestController
public class FeginUserMgController {
	
	@Autowired
	WinsunUserMgrFeginClient client;
	
	@Autowired
	WinsunUploadFeginClient uploadClient;
	
	@RequestMapping(value = "/mgr/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String name,
					   @RequestParam(required = false) String beginTime,
					   @RequestParam(required = false) String endTime,
					   @RequestParam(required = false) Integer deptid) {
		return client.list(name, beginTime, endTime, deptid);
	}
	
	@RequestMapping(value = "/mgr/user_info/{userId}",method = RequestMethod.GET)
	public Object userEdit(@PathVariable Integer userId) {
		return client.userEdit(userId);
	}
	
	@RequestMapping(value = "/mgr/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> user) {
		return client.add(user);
	}
	
	@RequestMapping(value = "/mgr/edit",method = RequestMethod.POST)
	public Object edit(@RequestParam Map<String,Object> user) {
		return client.edit(user);
	}
	
	@RequestMapping(value = "/mgr/freeze",method = RequestMethod.POST)
	public Object freeze(@RequestParam Integer userId) {
		return client.freeze(userId);
	}
	
	@RequestMapping(value = "/mgr/unfreeze",method = RequestMethod.POST)
	public Object unfreeze(@RequestParam Integer userId) {
		return client.unfreeze(userId);
	}
	
	@RequestMapping(value = "/mgr/delete",method = RequestMethod.POST)
	public Object delete(@RequestParam Integer userId) {
		return client.delete(userId);
	}
	
	@RequestMapping("/mgr/reset")
	public Object reset(@RequestParam Integer userId) {
		return client.reset(userId);
	}
	
	@RequestMapping("/mgr/changePwd")
	public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
		return client.changePwd(oldPwd, newPwd, rePwd);
	}

	@RequestMapping(value = "/mgr/setRole",method = RequestMethod.POST)
	 public Object setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds) {
		return client.setRole(userId, roleIds);
	}
	
	@RequestMapping(value = "/mgr/upload",method = RequestMethod.POST)
	public String upload(@RequestPart("file") MultipartFile picture) {
		return uploadClient.upload(picture);
	}

}
