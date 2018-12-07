package com.winsun.item.modular.manage.dao;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * @author fulonghuang
 * @date 2018/12/5
 */
public interface FreeTrialBroadbandListMapper {
    String maxDate();

    String maxDate2();

    List<Map<String, Object>> selectDataList(Page<Map<String, Object>> page, Map<String, Object> map);

    List<Map<String, Object>> selectDataList(Map<String, Object> map);
}
