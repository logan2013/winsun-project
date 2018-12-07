package com.cloud.app.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.admin.service.WinsunAddressFeginClient;

@RestController
public class FeginAddressController {
	
	@Autowired
	WinsunAddressFeginClient client;
	
	@RequestMapping(value ="/address/search")
	public Object searchAddress(String substName, String address) {
		return client.searchAddress(substName, address);
	}

}
