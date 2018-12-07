package com.winsun.item.modular.manage.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.modular.manage.service.IObstacleUserDeclarationListService;
import com.winsun.item.util.excel.ExportExcel;
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
@RequestMapping(value="/obstacle_user_declaration_list")
public class ObstacleUserDeclarationListController extends BaseController {
	@Autowired
	private IObstacleUserDeclarationListService generalService;

	@RequestMapping(value = "/list")
	public Object getDataList(HttpServletRequest request, @RequestParam Map<String, Object> map) {
		Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
		System.out.println(page);
		HttpSession session = request.getSession();
		String maxIMIDate = (String) session.getAttribute("maxIMIDate");
		if (org.apache.commons.lang3.StringUtils.isBlank(maxIMIDate)) {
			maxIMIDate = generalService.maxDate();
			session.setAttribute("maxIMIDate", maxIMIDate);
		}
		if (map.get("month_id") == null)
			map.put("month_id", session.getAttribute("maxIMIDate"));

		List<Map<String, Object>> list = generalService.selectDataList(page, map);
		page.setRecords(list);
		return super.packForBT(page);
	}

	@RequestMapping(value = "/maxDate")
	public Object maxDate() {
		return generalService.maxDate();
	}


	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> map) {
		ResponseEntity<byte[]> entity = null;
		try {
			//System.out.println(page);
			HttpSession session = request.getSession();
			String maxIMIDate = (String) session.getAttribute("maxIMIDate");
			if (org.apache.commons.lang3.StringUtils.isBlank(maxIMIDate)) {
				maxIMIDate = generalService.maxDate();
				session.setAttribute("maxIMIDate", maxIMIDate);
			}
			if (map.get("sum_date") == null)
				map.put("sum_date", session.getAttribute("maxIMIDate"));

			XSSFWorkbook workbook = generalService.exportExcel(request, response,map);
			entity = ExportExcel.toDownLoad(workbook, request, response, "障碍用户申告清单");
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
