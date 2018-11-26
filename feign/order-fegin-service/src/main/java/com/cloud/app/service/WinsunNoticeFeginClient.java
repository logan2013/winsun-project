package com.cloud.app.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.fallback.WinsunNoticeFeginClientFallback;
import com.cloud.app.model.Notice;


@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunNoticeFeginClientFallback.class)
public interface WinsunNoticeFeginClient {
	
	@RequestMapping("/ciop/notice/notice_update/{noticeId}")
	public String noticeUpdate(@PathVariable Integer noticeId);
	
	@RequestMapping("/ciop/notice/hello")
	public String hello();
	
	@RequestMapping(value = "/ciop/notice/list",method = RequestMethod.GET)
	public Object list(String condition);
	
	@RequestMapping(value = "/ciop/notice/add",method = RequestMethod.POST)
	public Object add(@RequestParam Map<String,Object> notice);
	
	@RequestMapping(value = "/ciop/notice/delete",method = RequestMethod.POST)
	public Object delete(@RequestParam Integer noticeId);
	
	@RequestMapping(value = "/ciop/notice/update",method = RequestMethod.POST)
	public Object update(@RequestParam Map<String,Object> notice);

}
