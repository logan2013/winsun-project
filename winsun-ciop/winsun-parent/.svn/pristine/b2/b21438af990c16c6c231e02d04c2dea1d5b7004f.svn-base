package com.winsun.item.modular.compare.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;

public interface ComparedRecordMapper {
	public List<Map<String, Object>> selectList(Page page,@Param("map") Map map);

	public List<Map<String, Object>> selectListByMap(Map map);

	public int updateRecordStatus(String id);

	public int addLog(Map map);

	public Map<String, Object> selectById(String id);

	public List<Map<String, Object>> selectPcList(Page page,@Param("map") Map map);

}
