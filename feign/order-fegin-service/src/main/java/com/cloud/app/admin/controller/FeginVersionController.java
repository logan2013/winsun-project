package com.cloud.app.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.admin.service.WinsunVersionFeginClient;

@RestController
public class FeginVersionController {
	
	@Autowired
	WinsunVersionFeginClient client;
	
	@RequestMapping("/version/update")
	public Object updateVersion(String version, String softSystem) {
		return client.updateVersion(version, softSystem);
	}

}
