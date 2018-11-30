package com.winsun.item.modular.compare.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.winsun.item.modular.compare.model.DimActDisc;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 电子比算推荐套餐表 服务类
 * </p>
 *
 * @author winsun
 * @since 2018-11-09
 */
public interface IDimActDiscService extends IService<DimActDisc> {

	List<Map<String, Object>> selectList(Page<Map<String, Object>> page,
			Map<String, Object> map);

	XSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;

	void importExcel(MultipartFile multipartFile) throws Exception;
}
