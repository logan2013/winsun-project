package com.winsun.item.modular.compare.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;

public interface IComparedRecordService {

	String addLogs(Map<String, Object> map, Map<String, Object> beatsMeal,
			List<Map<String, Object>> recommendedMeals, Map<String, Object> accountMap, String status);

	int updateRecordStatus(String id);

	List<Map<String, Object>> selectList(Page<Map<String, Object>> page,
			Map<String, Object> map);

	List<Map<String, Object>> selectPcList(Page<Map<String, Object>> page,
			Map<String, Object> map);

	Map<String, Object> selectById(String id);

}
