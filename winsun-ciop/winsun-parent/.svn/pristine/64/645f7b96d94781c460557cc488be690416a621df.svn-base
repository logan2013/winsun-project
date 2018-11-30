package com.winsun.item.modular.compare.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.modular.compare.model.DimActDisc;

/**
 * <p>
 * 电子比算推荐套餐表 Mapper 接口
 * </p>
 *
 * @author winsun
 * @since 2018-11-09
 */
public interface DimActDiscMapper extends BaseMapper<DimActDisc> {

	List<Map<String, Object>> selectList(Page<Map<String, Object>> page,
			@Param("map")Map<String, Object> map);
	List<Map<String, Object>> selectListByMap(Map<String, Object> map);
}
