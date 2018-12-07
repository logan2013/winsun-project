package com.winsun.item.modular.manage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.modular.manage.dao.ObstacleUserDeclarationListMapper;
import com.winsun.item.modular.manage.service.IObstacleUserDeclarationListService;
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
 * @date 2018/12/6
 */
@Service
@Transactional
public class ObstacleUserDeclarationListServiceImpl implements IObstacleUserDeclarationListService {

    @Autowired
    private ObstacleUserDeclarationListMapper obstacleUserDeclarationListMapper;

    @Override
    public String maxDate() {
        return obstacleUserDeclarationListMapper.maxDate();
    }

    @Override
    public List<Map<String, Object>> selectDataList(Page<Map<String, Object>> page, Map<String, Object> map) {
        return obstacleUserDeclarationListMapper.selectDataList(page,map);
    }

    @Override
    public XSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        List<Map<String, Object>> dataList = obstacleUserDeclarationListMapper.selectDataList(map);
        TreeNode treeNode = TreeNodeData.getObstacleUserDeclarationListTableThead();
        XSSFWorkbook workbook = ExcelTreeNodeHelper.getWorkbook2007(treeNode, dataList);
        return workbook;
    }}
