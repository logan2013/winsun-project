package com.winsun.item.modular.compare.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

public interface IAddressService {

	List<Map<String, Object>> searchAddress(Page<Map<String,Object>> page, Map<String, Object> map);

}
