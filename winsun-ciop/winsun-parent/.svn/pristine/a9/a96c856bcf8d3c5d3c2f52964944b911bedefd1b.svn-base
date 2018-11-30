
package com.winsun.item.core.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 导入Excel文件（支持“XLS”和“XLSX”格式）
 * @author ThinkGem
 * @version 2013-03-10
 */
public class ImportExcel {
	
	private static Logger log = LoggerFactory.getLogger(ImportExcel.class);
			
	/**
	 * 工作薄对象
	 */
	private Workbook wb;
	
	/**
	 * 工作表对象
	 */
	private Sheet sheet;
	
	/**
	 * 标题行号
	 */
	private int headerNum;
	
	/**
	 * 构造函数
	 * @param path 导入文件，读取第一个工作表
	 * @param headerNum 标题行号，数据行号=标题行号+1
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 */
	public ImportExcel(String fileName, int headerNum) 
			throws InvalidFormatException, IOException {
		this(new File(fileName), headerNum);
	}
	
	/**
	 * 构造函数
	 * @param path 导入文件对象，读取第一个工作表
	 * @param headerNum 标题行号，数据行号=标题行号+1
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 */
	public ImportExcel(File file, int headerNum) 
			throws InvalidFormatException, IOException {
		this(file, headerNum, 0);
	}

	/**
	 * 构造函数
	 * @param path 导入文件
	 * @param headerNum 标题行号，数据行号=标题行号+1
	 * @param sheetIndex 工作表编号
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 */
	public ImportExcel(String fileName, int headerNum, int sheetIndex) 
			throws InvalidFormatException, IOException {
		this(new File(fileName), headerNum, sheetIndex);
	}
	
	/**
	 * 构造函数
	 * @param path 导入文件对象
	 * @param headerNum 标题行号，数据行号=标题行号+1
	 * @param sheetIndex 工作表编号
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 */
	public ImportExcel(File file, int headerNum, int sheetIndex) 
			throws InvalidFormatException, IOException {
		this(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
	}
	
	/**
	 * 构造函数
	 * @param file 导入文件对象
	 * @param headerNum 标题行号，数据行号=标题行号+1
	 * @param sheetIndex 工作表编号
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 */
	public ImportExcel(MultipartFile multipartFile, int headerNum, int sheetIndex) 
			throws InvalidFormatException, IOException {
		this(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), headerNum, sheetIndex);
	}

	/**
	 * 构造函数
	 * @param path 导入文件对象
	 * @param headerNum 标题行号，数据行号=标题行号+1
	 * @param sheetIndex 工作表编号
	 * @throws InvalidFormatException 
	 * @throws IOException 
	 */
	public ImportExcel(String fileName, InputStream is, int headerNum, int sheetIndex) 
			throws InvalidFormatException, IOException {
		if (StringUtils.isBlank(fileName)){
			throw new RuntimeException("导入文档为空!");
		}else if(fileName.toLowerCase().endsWith("xls")){    
			this.wb = new HSSFWorkbook(is);    
        }else if(fileName.toLowerCase().endsWith("xlsx")){  
        	this.wb = new XSSFWorkbook(is);
        }else{  
        	throw new RuntimeException("文档格式不正确!");
        }  
		if (this.wb.getNumberOfSheets()<sheetIndex){
			throw new RuntimeException("文档中没有工作表!");
		}
		this.sheet = this.wb.getSheetAt(sheetIndex);
		this.headerNum = headerNum;
		log.debug("Initialize success.");
	}
	
	/**
	 * 获取行对象
	 * @param rownum
	 * @return
	 */
	public Row getRow(int rownum){
		return this.sheet.getRow(rownum);
	}

	/**
	 * 获取数据行号
	 * @return
	 */
	public int getDataRowNum(){
		return headerNum+1;
	}
	
	/**
	 * 获取最后一个数据行号
	 * @return
	 */
	public int getLastDataRowNum(){
		return this.sheet.getLastRowNum()+headerNum;
	}
	
	/**
	 * 获取最后一个列号
	 * @return
	 */
	public int getLastCellNum(){
		return this.getRow(headerNum).getLastCellNum();
	}
	
	/**
	 * 获取单元格值
	 * @param row 获取的行
	 * @param column 获取单元格列号
	 * @return 单元格值
	 */
	public Object getCellValue(Row row, int column){
		Object val = "";
		try{
			Cell cell = row.getCell(column);
			if (cell != null){
				if (cell.getCellType() == CellType.NUMERIC){
//					val = cell.getNumericCellValue();
					DecimalFormat df = new DecimalFormat("0");
					val = df.format(cell.getNumericCellValue());
				}else if (cell.getCellType() == CellType.STRING){
					val = cell.getStringCellValue();
				}else if (cell.getCellType() == CellType.FORMULA){
					val = cell.getCellFormula();
				}else if (cell.getCellType() == CellType.BOOLEAN){
					val = cell.getBooleanCellValue();
				}else if (cell.getCellType() == CellType.ERROR){
					val = cell.getErrorCellValue();
				}
			}
		}catch (Exception e) {
			return val;
		}
		return val;
	}

	public List<List<String>> getDataList(){
		List<List<String>> data = new ArrayList<>();
		int sum = sheet.getLastRowNum();
		for(int i=headerNum;i<=sum;i++){
			Row row = sheet.getRow(i);
			List<String> list = new ArrayList<>();
			int cellCount = row.getLastCellNum();
			for(int j=0;j<cellCount;j++){
				String result = "";
				result = getCellValue(row, j).toString();
				list.add(result.trim());
			}
			data.add(list);
		}
		return data;
	}
	
	public List<Map<String,Object>> getDataListMap(){
		List<Map<String,Object>> data = new ArrayList<>();
		int sum = sheet.getLastRowNum();
		for(int i=headerNum;i<=sum;i++){
			Row row = sheet.getRow(i);
			if(row!=null && row.getCell(0)!=null && !"".equals(row.getCell(0))){
				Map<String,Object> map = new LinkedHashMap<String,Object>();
				int cellCount = row.getLastCellNum();
				for(int j=0;j<cellCount;j++){
					String result = "";
					result = getCellValue(row, j).toString();
					map.put(index[j], result);
				}    
				data.add(map);
			}
		}
		return data;
	}
	
	static String[] index = {"a","b","c","d","e","f","g","h","i","j","k","l","m"
			,"n","o","p","q","r","s","t","u","v","w","x","y","z"
			,"aa","ab","ac","ad","ae","af","ag","ah","ai","aj","ak","al","am"
			,"an","ao","ap","aq","ar","as","at","au","av","aw","ax","ay","az"
			,"ba","bb","bc","bd","be","bf","bg","bh","bi","bj","bk","bl","bm"
			,"bn","bo","bp","bq","br","bs","bt","bu","bv","bw","bx","by","bz"
			,"ca","cb","cc","cd","ce","cf","cg","ch","ci","cj","ck","cl","cm"
			,"cn","co","cp","cq","cr","cs","ct","cu","cv","cw","cx","cy","cz"};
//	/**
//	 * 导入测试
//	 */
//	public static void main(String[] args) throws Throwable {
//		
//		ImportExcel ei = new ImportExcel("target/export.xlsx", 1);
//		
//		for (int i = ei.getDataRowNum(); i < ei.getLastDataRowNum(); i++) {
//			Row row = ei.getRow(i);
//			for (int j = 0; j < ei.getLastCellNum(); j++) {
//				Object val = ei.getCellValue(row, j);
//				System.out.print(val+", ");
//			}
//			System.out.print("\n");
//		}
//		
//	}

}
