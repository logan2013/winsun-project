package com.winsun.item.modular.compare.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.modular.compare.dao.AddressMapper;
import com.winsun.item.modular.compare.service.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private AddressMapper addressMapper;

	@Override
	public List<Map<String, Object>> searchAddress(
			Page<Map<String, Object>> page, Map<String, Object> map) {
		List<Map<String,Object>> list = addressMapper.searchAddress(page, map);
		return list;
	}
}
