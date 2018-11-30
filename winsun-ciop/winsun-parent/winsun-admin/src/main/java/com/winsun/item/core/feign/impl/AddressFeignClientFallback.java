package com.winsun.item.core.feign.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.core.feign.AddressFeignClient;
import com.winsun.item.core.util.ResponseEntity;
import com.winsun.item.modular.compare.service.IAddressService;

@Component
public class AddressFeignClientFallback implements AddressFeignClient{

	@Autowired
	private IAddressService addressService;
	
	public Object queryAddress(String address,Integer page, Integer size) {
		Page<Map<String,Object>> pageBean = new PageFactory<Map<String,Object>>().defaultPage();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("address", StringUtils.isBlank(address)?"":address);
		System.out.println(1);
		List<Map<String, Object>> list = addressService.searchAddress(pageBean, map);
		return ResponseEntity
				.newJSON("code", 200, "data", list);
	}
	
	@Override
	public Object match(String address, Integer page, Integer size) {
		return queryAddress(address,page,size);
	}

	@Override
	public Object query(String address, Integer page, Integer size) {
		return queryAddress(address,page,size);
	}

}
