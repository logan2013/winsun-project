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
			@RequestParam("page")Integer page,
			@RequestParam("size")Integer size);
	
	@GetMapping("/addr/address/fuzzy")
	public Object query(@RequestParam("address")String address,
			@RequestParam("page")Integer page,
			@RequestParam("size")Integer size);

}


