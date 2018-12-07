package com.winsun.item.modular.manage.dao;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map; /**
 * @author fulonghuang
 * @date 2018/12/6
 */
public interface ObstacleUserDeclarationListMapper {
    String maxDate();

    List<Map<String,Object>> selectDataList(Page<Map<String,Object>> page, Map<String, Object> map);

    List<Map<String,Object>> selectDataList(Map<String, Object> map);
}
