package com.winsun.item.util.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 根据TreeNode生成Exccel表
 *
 * @author ZERO
 */
public class ExcelTreeNodeHelper {

    /**
     * 生成多Sheel excel表
     *
     * @param treeNode
     * @param dataList
     * @return
     */
    public static HSSFWorkbook getSheelWorkbook(HSSFWorkbook workBook, TreeNode treeNode, List<Map<String, Object>> dataList, int sheetIndex, String sheetName) {
        Sheet sheet = workBook.createSheet();// 创建工作表
        workBook.setSheetName(sheetIndex, sheetName);
        sheet.setDefaultColumnWidth(10);
        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }
        setTableHeader(treeNode.getChildList(), sheet, arr);

        HSSFCellStyle style = workBook.createCellStyle(); // 样式对象
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        HSSFFont font = workBook.createFont();
        font.setFontName("Arial");
        style.setFont(font);

        if (dataList == null || dataList.size() == 0)
            return workBook;
        //System.out.println("========================================");
        List<String> keyList = treeNode.getKeyList();
        Iterator<Map<String, Object>> it = dataList.iterator();
        for (int i = max + 1; it.hasNext(); i++) {
            Map<String, Object> dataMap = (Map<String, Object>) it.next();
            Iterator<String> it2 = keyList.iterator();
            Row row = sheet.createRow(i);
            for (int j = 0; it2.hasNext(); j++) {
                Object obj = dataMap.get(it2.next());
                if (obj != null) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(style);
                    if (!"null".equals(String.valueOf(obj))) {
                        cell.setCellValue(String.valueOf(obj));
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
        }
        return workBook;
    }

    /**
     * 创建表头
     *
     * @param workBook
     * @param treeNode
     * @param sheetName
     * @param sheetIndex
     */
    public static void createSheetData(XSSFWorkbook workBook, TreeNode treeNode, String sheetName, int sheetIndex) {
        Sheet sheet = workBook.createSheet();// 创建工作表
        workBook.setSheetName(sheetIndex, sheetName);
//		TreeNode treeNode = TestTree.getTreeNode();
        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }
        setTableHeader(treeNode.getChildList(), sheet, arr);
    }

    public static XSSFWorkbook getWorkbook2007(TreeNode treeNode, List<Map<String, Object>> dataList) {
        XSSFWorkbook workBook = new XSSFWorkbook();// 创建工作本
        Sheet sheet = workBook.createSheet("1");// 创建工作表
        sheet.setDefaultColumnWidth(10);
        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }
        setTableHeader(treeNode.getChildList(), sheet, arr);

        XSSFCellStyle style = workBook.createCellStyle(); // 样式对象
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        XSSFFont font = workBook.createFont();
        font.setFontName("Arial");
        style.setFont(font);

        if (dataList == null || dataList.size() == 0)
            return workBook;
//		System.out.println("========================================");
        List<String> keyList = treeNode.getKeyList();
        Iterator<Map<String, Object>> it = dataList.iterator();
        for (int i = max + 1; it.hasNext(); i++) {
            Map<String, Object> dataMap = (Map<String, Object>) it.next();
            Iterator<String> it2 = keyList.iterator();
            Row row = sheet.createRow(i);
            for (int j = 0; it2.hasNext(); j++) {
                Object obj = dataMap.get(it2.next());
                if (obj != null) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(style);
                    if (!"null".equals(String.valueOf(obj))) {
                        cell.setCellValue(String.valueOf(obj));
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
        }
        return workBook;
    }

    /**
     * 生成excel表
     *
     * @param treeNode
     * @param dataList
     * @return
     */
    public static HSSFWorkbook getWorkbook2003(TreeNode treeNode, List<Map<String, Object>> dataList) {
        HSSFWorkbook workBook = new HSSFWorkbook();// 创建工作本
        Sheet sheet = workBook.createSheet("1");// 创建工作表
        sheet.setDefaultColumnWidth(10);
        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }
        setTableHeader(treeNode.getChildList(), sheet, arr);

        HSSFCellStyle style = workBook.createCellStyle(); // 样式对象
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        HSSFFont font = workBook.createFont();
        font.setFontName("Arial");
        style.setFont(font);

        if (dataList == null || dataList.size() == 0)
            return workBook;
        //System.out.println("========================================");
        List<String> keyList = treeNode.getKeyList();
        Iterator<Map<String, Object>> it = dataList.iterator();
        for (int i = max + 1; it.hasNext(); i++) {
            Map<String, Object> dataMap = (Map<String, Object>) it.next();
            Iterator<String> it2 = keyList.iterator();
            Row row = sheet.createRow(i);
            for (int j = 0; it2.hasNext(); j++) {
                Object obj = dataMap.get(it2.next());
                if (obj != null) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(style);
                    if (!"null".equals(String.valueOf(obj))) {
                        cell.setCellValue(String.valueOf(obj));
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
        }
        return workBook;
    }

    /**
     * 生成excel表 带图片，超链接
     *
     * @param treeNode
     * @param dataList
     * @return
     */
    public static HSSFWorkbook getWorkbookA(TreeNode treeNode, List<Map<String, Object>> dataList, String basePath) {
        HSSFWorkbook workBook = new HSSFWorkbook();// 创建工作本
        Sheet sheet = workBook.createSheet("1");// 创建工作表
        sheet.setDefaultColumnWidth(10);
        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }
        setTableHeader(treeNode.getChildList(), sheet, arr);

        HSSFCellStyle style = workBook.createCellStyle(); // 样式对象
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        HSSFFont font = workBook.createFont();
        font.setFontName("Arial");
        style.setFont(font);

        //图片超链接
        HSSFCellStyle linkStyle = workBook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        HSSFFont cellFont = workBook.createFont();
        cellFont.setUnderline((byte) 1);
        linkStyle.setFont(cellFont);

        if (dataList == null || dataList.size() == 0)
            return workBook;
        //System.out.println("========================================");
        List<String> keyList = treeNode.getKeyList();
        Iterator<Map<String, Object>> it = dataList.iterator();
//		String basePath = "http://bs.winsun-aly.com:18093"; 
        for (int i = max + 1; it.hasNext(); i++) {
            Map<String, Object> dataMap = (Map<String, Object>) it.next();
            Iterator<String> it2 = keyList.iterator();
            Row row = sheet.createRow(i);
            for (int j = 0; it2.hasNext(); j++) {
                Object obj = null;
                String key = it2.next().toString();
                if (key.equals("IMGURL1") || key.equals("IMGURL2") || key.equals("IMGURL3")) { //图片路径超链接
                    String[] urls = dataMap.get("IMGURL").toString().split(",");
                    if (key.equals("IMGURL1") && urls.length >= 1) {
                        obj = basePath + urls[0];
                    } else if (key.equals("IMGURL2") && urls.length >= 2) {
                        obj = basePath + urls[1];
                    } else if (key.equals("IMGURL3") && urls.length >= 3) {
                        obj = basePath + urls[2];
                    }
                    if (obj != null) {
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(linkStyle);
                        if (!"null".equals(String.valueOf(obj))) {
                            cell.setCellValue(String.valueOf(obj));
                        } else {
                            cell.setCellValue("");
                        }
                    }
                } else {
                    obj = dataMap.get(key);
                    if (obj != null) {
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(style);
                        if (!"null".equals(String.valueOf(obj))) {
                            cell.setCellValue(String.valueOf(obj));
                        } else {
                            cell.setCellValue("");
                        }
                    }
                }
            }
        }
        return workBook;
    }

    /**
     * 已有表头填充表身
     * 生成excel表
     *
     * @param treeNode
     * @param dataList
     * @return
     */
    public static HSSFWorkbook getWorkbook(HSSFWorkbook workBook, TreeNode treeNode, List<Map<String, Object>> dataList) {
        Sheet sheet = workBook.getSheet("1");// 创建工作表
        sheet.setDefaultColumnWidth(10);

        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }

        HSSFCellStyle style = workBook.createCellStyle(); // 样式对象
        style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
        HSSFFont font = workBook.createFont();
        font.setFontName("Arial");
        style.setFont(font);

        if (dataList == null || dataList.size() == 0)
            return workBook;
        //System.out.println("========================================");
        List<String> keyList = treeNode.getKeyList();
        Iterator<Map<String, Object>> it = dataList.iterator();
        for (int i = max + 1; it.hasNext(); i++) {
            Map<String, Object> dataMap = (Map<String, Object>) it.next();
            Iterator<String> it2 = keyList.iterator();
            Row row = sheet.createRow(i);
            for (int j = 0; it2.hasNext(); j++) {
                Object obj = dataMap.get(it2.next());
                if (obj != null) {
                    Cell cell = row.createCell(j);
                    cell.setCellStyle(style);
                    if (!"null".equals(String.valueOf(obj))) {
                        cell.setCellValue(String.valueOf(obj));
                    } else {
                        cell.setCellValue("");
                    }
                }
            }
        }
        return workBook;
    }

    /**
     * 创建多sheet表头
     *
     * @param workBook
     * @param sheetName
     * @param sheetIndex
     * @param treeNode
     */
    public static void createSheetData(HSSFWorkbook workBook, String sheetName, int sheetIndex, TreeNode treeNode) {
        Sheet sheet = workBook.createSheet();// 创建工作表
        workBook.setSheetName(sheetIndex, sheetName);
//		TreeNode treeNode = TestTree.getTreeNode();
        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }
        setTableHeader(treeNode.getChildList(), sheet, arr);
    }

    /**
     * 初始化表头
     *
     * @param treeNode
     * @return
     */
    public static XSSFWorkbook getWorkbook2007(TreeNode treeNode) {
        XSSFWorkbook workBook = new XSSFWorkbook();// 创建工作本
//		TreeNode treeNode = TestTree.getTreeNode();
        Sheet sheet = workBook.createSheet();// 创建工作表
        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }
        setTableHeader(treeNode.getChildList(), sheet, arr);
        return workBook;
    }

    /**
     * 初始化表头
     *
     * @param treeNode
     * @return
     */
    public static HSSFWorkbook getWorkbook2003(TreeNode treeNode) {
        HSSFWorkbook workBook = new HSSFWorkbook();// 创建工作本
        Sheet sheet = workBook.createSheet();// 创建工作表
        int max = treeNode.getMaxLevel();// 获取最大的层次 n-1
        int[] arr = new int[max + 1];// n层
        for (int i = 0; i < arr.length; i++) {// 创建行
            sheet.createRow(i);// 创建n行表头
            arr[i] = 0;
        }
        setTableHeader(treeNode.getChildList(), sheet, arr);
        return workBook;
    }

    /**
     * 利用递归遍历子数据，向excel填入excel表头
     *
     * @param childList
     * @param sheet
     * @param arr
     */
    private static void setTableHeader(List<TreeNode> childList, Sheet sheet, int[] arr) {
        for (TreeNode node : childList) {
            CellRangeAddress cra;
            if (node.isLeaf()) {
                cra = new CellRangeAddress(node.level, arr.length - 1, arr[node.level],
                        arr[node.level] + node.leafSize - 1);

                if (node.level != arr.length - 1) {
                    for (int i = node.level + 1; i < arr.length; i++) {
                        arr[i] += 1;
                    }
                }
            } else {// 不是叶子节点
                cra = new CellRangeAddress(node.level, node.level, arr[node.level],
                        arr[node.level] + node.leafSize - 1);
                //sheet.addMergedRegion(cra);// 合并单元格
            }
            boolean colFlag = cra.getFirstColumn() == cra.getLastColumn();
            boolean rowFlag = cra.getFirstRow() == cra.getLastRow();
            boolean crFlag = colFlag && rowFlag;//是否同一个单元格
            if (!crFlag && (node.level != arr.length - 1 || arr[node.level] != arr[node.level] + node.leafSize - 1)) {
                //System.err.println(cra.getFirstColumn() + ",col''last:" + cra.getLastColumn());
                //System.err.println(cra.getNumberOfCells());
                //System.err.println(cra.getFirstRow() + ",Row''last:" + cra.getLastRow());
                sheet.addMergedRegion(cra);// 合并单元格
            }

            Row row = sheet.getRow(node.level);
            Cell cell = row.createCell(arr[node.level]);
            cell.setCellValue(node.getNodeName());

            XSSFCellStyle cellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();// 设置自动换行以及居中样式
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
            cell.setCellStyle(cellStyle);

            arr[node.level] += node.leafSize;// 更新X轴指针位置
            if (!node.isLeaf()) {
                setTableHeader(node.getChildList(), sheet, arr);
            }
        }
    }

    /**
     * 遍历子数据
     *
     * @param childList
     * @param sheet
     * @param arr
     */
    public static void showChildNode(List<TreeNode> childList, Sheet sheet, int[] arr) {
        for (TreeNode node : childList) {
            CellRangeAddress cra;
            if (node.isLeaf()) {
                cra = new CellRangeAddress(node.level, arr.length - 1, arr[node.level], arr[node.level] + node.leafSize - 1);
                if (node.level != arr.length - 1) {
                    for (int i = node.level + 1; i < arr.length; i++) {
                        arr[i] += 1;
                    }
                }
            } else {//不是叶子节点
                cra = new CellRangeAddress(node.level, node.level, arr[node.level], arr[node.level] + node.leafSize - 1);
            }
            sheet.addMergedRegion(cra);
            Row row = sheet.getRow(node.level);
            Cell cell = row.createCell(arr[node.level]);
            cell.setCellValue(node.getNodeName());
            XSSFCellStyle cellStyle = (XSSFCellStyle) sheet.getWorkbook().createCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
            cellStyle.setWrapText(true);//自动换行
            cell.setCellStyle(cellStyle);
            arr[node.level] += node.leafSize;
            if (node.getChildList() != null) {
                showChildNode(node.getChildList(), sheet, arr);
            }
        }
    }

}
