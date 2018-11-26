package com.winsun.item.modular.compare.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.winsun.item.core.util.excel.ExcelTreeNodeHelper;
import com.winsun.item.core.util.excel.ImportExcel;
import com.winsun.item.core.util.excel.TreeNode;
import com.winsun.item.modular.compare.dao.DimActDiscMapper;
import com.winsun.item.modular.compare.model.DimActDisc;
import com.winsun.item.modular.compare.service.IDimActDiscService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 电子比算推荐套餐表 服务实现类
 * </p>
 *
 * @author winsun
 * @since 2018-11-09
 */
@Service
public class DimActDiscServiceImpl extends ServiceImpl<DimActDiscMapper, DimActDisc> implements IDimActDiscService {

    @Autowired
    private DimActDiscMapper dimActDiscMapper;

    @Override
    public List<Map<String, Object>> selectList(Page<Map<String, Object>> page,
                                                Map<String, Object> map) {
        return dimActDiscMapper.selectList(page, map);
    }

    @Override
    public XSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Map<String, Object>> dataList = dimActDiscMapper.selectListByMap(new HashMap<>());

        TreeNode treeNode = new TreeNode(-1, "", null);
        TreeNode treeNode0 = new TreeNode(0, "区域", "region_type", treeNode);
        TreeNode treeNode1 = new TreeNode(1, "套餐名称", "disc_name", treeNode);
        TreeNode treeNode2 = new TreeNode(2, "套餐档次", "disc_value", treeNode);
        TreeNode treeNode3 = new TreeNode(3, "速率", "speed_value_desc", treeNode);
        TreeNode treeNode4 = new TreeNode(4, "移动内容", treeNode);
        TreeNode treeNode5 = new TreeNode(5, "副卡（张）", "fk_desc", treeNode);
        TreeNode treeNode6 = new TreeNode(6, "天翼高清（路）", "itv_desc", treeNode);
        TreeNode treeNode7 = new TreeNode(7, "优惠补贴（三选一）", treeNode);
        TreeNode treeNode8 = new TreeNode(8, "国内语音（分钟）", "mou_call", treeNode4);
        TreeNode treeNode9 = new TreeNode(9, "全国流量", "stm_data", treeNode4);
        TreeNode treeNode10 = new TreeNode(10, "终端补贴", "bt_value", treeNode7);
        TreeNode treeNode11 = new TreeNode(11, "橙分期", "cfq_desc", treeNode7);
        TreeNode treeNode12 = new TreeNode(12, "翼支付红包", "yzf_hb", treeNode7);
        treeNode.calLeavesAmount(treeNode);
        treeNode.calNodeLevel(treeNode);
        XSSFWorkbook workbook = ExcelTreeNodeHelper.getWorkbook2007(treeNode, dataList);
        return workbook;
    }

    @Override
    public void importExcel(MultipartFile multipartFile) throws Exception {
        ImportExcel importExcel = new ImportExcel(multipartFile, 3, 0);
        DimActDisc dimActDisc = new DimActDisc();
        List<Map<String, Object>> dataListMap = importExcel.getDataListMap();
        for (Map<String, Object> map : dataListMap) {
            if (map.containsValue("")){
                continue;
            }
            dimActDisc.setRegionType((String) map.get("a"));
            dimActDisc.setDiscName((String) map.get("b"));
            dimActDisc.setDiscValue(new BigDecimal(map.get("c").toString()));
            dimActDisc.setSpeedValue(new BigDecimal(map.get("d").toString()));
            dimActDisc.setSpeedValueDesc((String) map.get("e"));
            dimActDisc.setMouCall(new BigDecimal(map.get("f").toString()));
            dimActDisc.setStmData((String) map.get("h"));
            dimActDisc.setFkValue(new BigDecimal(map.get("i").toString()));
            dimActDisc.setFkDesc((String) map.get("j"));
            dimActDisc.setItvValue(new BigDecimal(map.get("k").toString()));
            dimActDisc.setItvDesc((String) map.get("l"));
            dimActDisc.setBtType((String) map.get("n"));
            dimActDisc.setCfqDesc((String) map.get("p"));
            dimActDisc.setYzfHb((String) map.get("r"));
            dimActDisc.setIsOld("0");
            dimActDisc.setDelFlag("0");
            dimActDisc.setStmDataValue((String) map.get("g"));
            dimActDisc.setBtValue((String) map.get("m"));
            dimActDisc.setCfqValue((String) map.get("o"));
            dimActDisc.setYzfHbValue((String) map.get("q"));
            insert(dimActDisc);
        }
    }

}
