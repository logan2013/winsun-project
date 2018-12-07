package com.winsun.item.modular.manage.service;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author fulonghuang
 * @date 2018/12/3
 */
public interface IInstallationMaintenanceInformationService {

    List<Map<String, Object>> selectDataList(Page<Map<String, Object>> page, Map<String, Object> map);


    List<Map<String, Object>> selectPqDataList(Page<Map<String, Object>> page,Map<String, Object> map);

    String maxDate();

    XSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response,Map<String, Object> map);

    String maxPQDate();

    XSSFWorkbook exportExcelPQ(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map);
}
