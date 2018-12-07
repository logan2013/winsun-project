package com.winsun.item.core.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winsun.item.core.feign.impl.AddressFeignClientFallback;


@FeignClient(value = "${winsum.address.serviceId}",fallback = AddressFeignClientFallback.class)
public interface AddressFeignClient {
	
	@GetMapping("/addr/address/match")
	public Object match(@RequestParam("address")String address,
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "limit",required = false) Integer limit);
	
	@GetMapping("/addr/address/fuzzy")
	public Object query(@RequestParam("address")String address,
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "limit",required = false) Integer limit);
	
	@GetMapping("/addr/address/matchPart")
	public Object matchPart(@RequestParam("address")String address,
			@RequestParam(name = "page",required = false) Integer page,
			@RequestParam(name = "limit",required = false) Integer limit);

}


