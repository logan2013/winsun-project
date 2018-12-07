package com.winsun.item.modular.manage.dao;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

public interface InstallationMaintenanceInformationMapper {

    List<Map<String, Object>> selectDataList(Page<Map<String, Object>> page, Map<String, Object> map);

    List<Map<String, Object>> selectPqDataList(Page<Map<String, Object>> page, Map<String, Object> map);

    List<Map<String,Object>> selectDataList(Map<String, Object> map);

    List<Map<String,Object>> selectPqDataList(Map<String, Object> map);

    String maxDate();

    String maxPQDate();

}
