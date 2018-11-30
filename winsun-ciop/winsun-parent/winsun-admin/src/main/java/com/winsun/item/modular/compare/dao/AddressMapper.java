package com.winsun.item.modular.compare.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;

public interface AddressMapper {

	List<Map<String, Object>> searchAddress(Page<Map<String, Object>> page,
			@Param("map") Map<String, Object> map);

}
