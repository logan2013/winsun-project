package com.winsun.item.modular.manage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.modular.manage.dao.InstallationMaintenanceInformationMapper;
import com.winsun.item.modular.manage.service.IInstallationMaintenanceInformationService;
import com.winsun.item.util.Data;
import com.winsun.item.util.excel.ExcelTreeNodeHelper;
import com.winsun.item.util.excel.TreeNode;
import com.winsun.item.util.excel.TreeNodeData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author fulonghuang
 * @date 2018/12/4
 */
@Service
@Transactional
public class InstallationMaintenanceInformationServiceImpl implements IInstallationMaintenanceInformationService {

    @Autowired
    private InstallationMaintenanceInformationMapper installationMaintenanceInformationMapper;

    @Override
    public List<Map<String, Object>> selectDataList(Page<Map<String, Object>> page, Map<String, Object> map) {
        return installationMaintenanceInformationMapper.selectDataList(page,map);
    }

    @Override
    public List<Map<String, Object>> selectPqDataList(Page<Map<String, Object>> page, Map<String, Object> map) {
        return installationMaintenanceInformationMapper.selectPqDataList(page,map);
    }

    @Override
    public String maxDate() {
            return installationMaintenanceInformationMapper.maxDate();
    }

    @Override
    public String maxPQDate() {
        return installationMaintenanceInformationMapper.maxPQDate();
    }

    @Override
    public XSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        List<Map<String, Object>> dataList = installationMaintenanceInformationMapper.selectDataList(map);
        TreeNode treeNode = TreeNodeData.getInstallationMaintenanceInformationTableThead();
        XSSFWorkbook workbook = ExcelTreeNodeHelper.getWorkbook2007(treeNode, dataList);
        return workbook;
    }

    @Override
    public XSSFWorkbook exportExcelPQ(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        List<Map<String, Object>> dataList = installationMaintenanceInformationMapper.selectPqDataList(map);
        TreeNode treeNode = TreeNodeData.getInstallationMaintenanceInformationPqTableThead();
        XSSFWorkbook workbook = ExcelTreeNodeHelper.getWorkbook2007(treeNode, dataList);
        return workbook;
    }

}
