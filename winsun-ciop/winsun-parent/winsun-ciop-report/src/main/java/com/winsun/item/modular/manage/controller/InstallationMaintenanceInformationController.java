package com.winsun.item.modular.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.modular.manage.service.IInstallationMaintenanceInformationService;
import com.winsun.item.util.excel.ExportExcel;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/installation_maintenance_information")
public class InstallationMaintenanceInformationController extends BaseController {
    @Autowired
    private IInstallationMaintenanceInformationService installationMaintenanceInformationService;

    @RequestMapping(value = "/list")
    public Object getDataList(HttpServletRequest request, @RequestParam Map<String, Object> map) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
        System.out.println(page);
        HttpSession session = request.getSession();
        String maxIMIDate = (String) session.getAttribute("maxIMIDate");
        if (StringUtils.isBlank(maxIMIDate)) {
            maxIMIDate = installationMaintenanceInformationService.maxDate();
            session.setAttribute("maxIMIDate", maxIMIDate);
        }
        if (map.get("sum_date") == null)
            map.put("sum_date", session.getAttribute("maxIMIDate"));

        List<Map<String, Object>> list = installationMaintenanceInformationService.selectDataList(page, map);
        page.setRecords(list);
        return super.packForBT(page);
    }

    @RequestMapping(value = "/maxDate")
    public Object maxDate() {
        return installationMaintenanceInformationService.maxDate();
    }

    @RequestMapping(value = "/pq/list")
    public Object getPQDataList(HttpServletRequest request, @RequestParam Map<String, Object> map) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
        System.out.println(page);
        HttpSession session = request.getSession();
        String maxIMIDate = (String) session.getAttribute("maxIMIDate");
        if (StringUtils.isBlank(maxIMIDate)) {
            maxIMIDate = installationMaintenanceInformationService.maxPQDate();
            session.setAttribute("maxIMIDate", maxIMIDate);
        }
        if (map.get("sum_date") == null)
            map.put("sum_date", session.getAttribute("maxIMIDate"));

        List<Map<String, Object>> list = installationMaintenanceInformationService.selectPqDataList(page, map);
        page.setRecords(list);
        return super.packForBT(page);
    }

    @RequestMapping(value = "/pq/maxDate")
    public Object maxPQDate() {
        return installationMaintenanceInformationService.maxPQDate();
    }


    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> map) {
    	ResponseEntity<byte[]> entity = null;
    	try {
            HttpSession session = request.getSession();
            String maxIMIDate = (String) session.getAttribute("maxIMIDate");
            if (StringUtils.isBlank(maxIMIDate)) {
                maxIMIDate = installationMaintenanceInformationService.maxDate();
                session.setAttribute("maxIMIDate", maxIMIDate);
            }
            if (map.get("sum_date") == null)
                map.put("sum_date", session.getAttribute("maxIMIDate"));

            XSSFWorkbook workbook = installationMaintenanceInformationService.exportExcel(request, response, map);
            entity = ExportExcel.toDownLoad(workbook, request, response, "装维信息");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return entity;
    }

    @RequestMapping(value = "/pq/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> exportExcelPQ(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> map) {
    	ResponseEntity<byte[]> entity = null;
    	try {
            HttpSession session = request.getSession();
            String maxIMIDate = (String) session.getAttribute("maxIMIDate");
            if (StringUtils.isBlank(maxIMIDate)) {
                maxIMIDate = installationMaintenanceInformationService.maxPQDate();
                session.setAttribute("maxIMIDate", maxIMIDate);
            }
            if (map.get("sum_date") == null)
                map.put("sum_date", session.getAttribute("maxIMIDate"));

            XSSFWorkbook workbook = installationMaintenanceInformationService.exportExcelPQ(request, response, map);
            entity = ExportExcel.toDownLoad(workbook, request, response, "装维信息_片区维度");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

}
