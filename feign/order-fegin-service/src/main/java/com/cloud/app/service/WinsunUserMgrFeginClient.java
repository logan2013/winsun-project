package com.cloud.app.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.app.fallback.WinsunUserMgrFeginClientFallback;
import com.cloud.app.model.User;

@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunUserMgrFeginClientFallback.class)
public interface WinsunUserMgrFeginClient {
	
	@RequestMapping(value = "/ciop/mgr/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String name,
					   @RequestParam(required = false) String beginTime,
					   @RequestParam(required = false) String endTime,
					   @RequestParam(required = false) Integer deptid);
	
	@RequestMapping(value = "/ciop/mgr/user_info/{userId}",method = RequestMethod.GET)
	public Object userEdit(@PathVariable Integer userId);
	
	@RequestMapping(value = "/ciop/mgr/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> user);
	
	@RequestMapping(value = "/ciop/mgr/edit",method = RequestMethod.POST)
	public Object edit(@RequestParam Map<String,Object> user);
	
	@RequestMapping(value = "/ciop/mgr/freeze",method = RequestMethod.POST)
	public Object freeze(@RequestParam Integer userId);
	
	@RequestMapping(value = "/ciop/mgr/unfreeze",method = RequestMethod.POST)
	public Object unfreeze(@RequestParam Integer userId);
	
	@RequestMapping(value = "/ciop/mgr/delete",method = RequestMethod.POST)
	public Object delete(@RequestParam Integer userId);
	
	@RequestMapping("/ciop/mgr/reset")
	public Object reset(@RequestParam Integer userId);
	
	@RequestMapping("/ciop/mgr/changePwd")
	public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd);

	@RequestMapping(value = "/ciop/mgr/setRole",method = RequestMethod.POST)
	 public Object setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds);
	
	

}
