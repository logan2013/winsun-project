package com.cloud.app.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.address.service.AddressFeignClient;

@RestController
public class FeginAddressServiceController {
	
	@Autowired
	AddressFeignClient addressClient;

	@GetMapping("/address/match")
	public Object match(@RequestParam("address")String address,
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "limit",required = false) Integer limit) {
		return addressClient.match(address, page, limit);
	}
	
	@GetMapping("/address/fuzzy")
	public Object query(@RequestParam("address")String address,
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "limit",required = false) Integer limit) {
		return addressClient.query(address, page, limit);
	}
	
	@GetMapping("/address/matchPart")
	public Object matchPart(@RequestParam("address")String address,
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "limit",required = false) Integer limit) {
		return addressClient.matchPart(address, page, limit);
	}
}
