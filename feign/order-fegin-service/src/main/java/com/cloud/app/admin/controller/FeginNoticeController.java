package com.cloud.app.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.app.admin.service.WinsunNoticeFeginClient;
import com.cloud.app.model.Notice;

@Controller
public class FeginNoticeController {
	
	@Autowired
	WinsunNoticeFeginClient client;
	
	@RequestMapping("/notice/notice_update/{noticeId}")
	public String noticeUpdate(@PathVariable Integer noticeId) {
		return client.noticeUpdate(noticeId);
	}
	
	@RequestMapping("/notice/hello")
	public String hello() {
		return client.hello();
	}
	
	@RequestMapping(value = "/notice/list",method = RequestMethod.GET)
	@ResponseBody
	public Object list(String condition) {
		return client.list(condition);
	}
	
	@RequestMapping(value = "/notice/add",method = RequestMethod.POST)
	@ResponseBody
	public Object add(@RequestParam Map<String,Object> notice) {
		return client.add(notice);
	}
	
	@RequestMapping(value = "/notice/delete",method = RequestMethod.POST)
	@ResponseBody
	public Object delete(@RequestParam Integer noticeId) {
		return client.delete(noticeId);
	}
	
	@RequestMapping(value = "/notice/update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(@RequestParam Map<String,Object> notice) {
		return client.update(notice);
	}

}
