package com.winsun.item.modular.system.dao;

import com.winsun.item.modular.system.model.Version;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fulonghuang
 * @date 2018/11/26
 */
public interface VersionMapper {
    Version selectNewVersion(@Param("softSystem") String softSystem, @Param("versionType")String versionType);
}
