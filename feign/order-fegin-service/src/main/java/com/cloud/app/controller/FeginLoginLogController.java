package com.cloud.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.service.WinsunLoginLogFeginClient;

@RestController
public class FeginLoginLogController {
	
	@Autowired
	WinsunLoginLogFeginClient client;
	
	@RequestMapping(value = "/loginLog/list",method = RequestMethod.GET)
	public Object list(@RequestParam(required = false) String beginTime, 
					   @RequestParam(required = false) String endTime, 
					   @RequestParam(required = false) String logName) {
		return client.list(beginTime, endTime, logName);
	}
	
	@RequestMapping(value = "/loginLog/delLoginLog",method = RequestMethod.POST)
	public Object delLog() {
		return client.delLog();
	}

}
