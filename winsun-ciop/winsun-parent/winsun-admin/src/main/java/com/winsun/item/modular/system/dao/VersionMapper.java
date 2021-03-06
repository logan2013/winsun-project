package com.winsun.item.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.modular.system.model.Version;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author fulonghuang
 * @date 2018/11/26
 */
public interface VersionMapper extends BaseMapper<Version> {
    Version selectNewVersion(@Param("softSystem") String softSystem, @Param("versionType")String versionType);

    List<Map<String,Object>> selectList(Page<Map<String,Object>> page, @Param("map")Map<String, Object> map);
}
