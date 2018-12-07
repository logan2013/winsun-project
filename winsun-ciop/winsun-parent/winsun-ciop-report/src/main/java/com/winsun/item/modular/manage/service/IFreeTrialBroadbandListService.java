package com.winsun.item.modular.manage.service;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
/**
 * @author fulonghuang
 * @date 2018/12/5
 */
public interface IFreeTrialBroadbandListService {
    String maxDate();

    List<Map<String,Object>> selectDataList(Page<Map<String,Object>> page, Map<String, Object> map);

    XSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map);
}
