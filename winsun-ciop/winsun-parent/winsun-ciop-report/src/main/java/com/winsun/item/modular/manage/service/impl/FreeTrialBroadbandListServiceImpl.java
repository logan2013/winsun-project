package com.winsun.item.modular.manage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.modular.manage.dao.FreeTrialBroadbandListMapper;
import com.winsun.item.modular.manage.service.IFreeTrialBroadbandListService;
import com.winsun.item.util.Data;
import com.winsun.item.util.excel.ExcelTreeNodeHelper;
import com.winsun.item.util.excel.TreeNode;
import com.winsun.item.util.excel.TreeNodeData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author fulonghuang
 * @date 2018/12/5
 */
@Service
@Controller
public class FreeTrialBroadbandListServiceImpl implements IFreeTrialBroadbandListService {

    @Autowired
    private FreeTrialBroadbandListMapper freeTrialBroadbandListMapper;

    @Override
    public String maxDate() {
        return freeTrialBroadbandListMapper.maxDate();
    }

    @Override
    public List<Map<String, Object>> selectDataList(Page<Map<String, Object>> page, Map<String, Object> map) {
        return freeTrialBroadbandListMapper.selectDataList(page,map);
    }

    @Override
    public XSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {
        List<Map<String, Object>> dataList = freeTrialBroadbandListMapper.selectDataList(map);
        TreeNode treeNode = TreeNodeData.getFreeTrialBroadbandListTableThead();
        XSSFWorkbook workbook = ExcelTreeNodeHelper.getWorkbook2007(treeNode, dataList);
        return workbook;
    }
}
