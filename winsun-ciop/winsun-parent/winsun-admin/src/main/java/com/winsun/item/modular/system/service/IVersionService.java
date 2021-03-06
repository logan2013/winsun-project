package com.winsun.item.modular.system.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.winsun.item.modular.system.model.Version;

import java.util.List;
import java.util.Map;

/**
 * @author fulonghuang
 * @date 2018/11/26
 */
public interface IVersionService extends IService<Version> {
    Version updateVersion(String version, String softSystem);

    List<Map<String,Object>> selectList(Page<Map<String,Object>> page, Map<String, Object> map);
}
