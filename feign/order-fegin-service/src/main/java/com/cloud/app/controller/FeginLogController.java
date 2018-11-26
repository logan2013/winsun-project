package com.cloud.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.service.WinsunLogFeginClient;

@RestController
public class FeginLogController {
	
	@Autowired
	WinsunLogFeginClient client;
	
	@RequestMapping(value = "/log/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String beginTime,
					   @RequestParam(required = false) String endTime,
					   @RequestParam(required = false) String logName,
					   @RequestParam(required = false) Integer logType) {
		return client.list(beginTime, endTime, logName, logType);
	}
	
	
	@RequestMapping(value = "/log/detail/{id}",method = RequestMethod.GET)
	public Object detail(@PathVariable Integer id) {
		return client.detail(id);
	}
	
	
	@RequestMapping(value = "/ciop/log/delLog",method = RequestMethod.POST)
	public Object delLog() {
		return client.delLog();
	}

}
