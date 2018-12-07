package com.cloud.app.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.report.service.ReportDownFeginClient;
import com.cloud.app.report.service.ReportObstacleFeginClient;

@RestController
public class ReportObstacleController {
	
	@Autowired
	ReportObstacleFeginClient reportObstacleClient;
	
	@Autowired
	ReportDownFeginClient downClient;
	
	@RequestMapping(value="/obstacle_user_declaration_list/list")
	public Object getDataList(@RequestParam Map<String, Object> map) {
		return reportObstacleClient.getDataList(map);
	}
	
	@RequestMapping(value = "/obstacle_user_declaration_list/maxDate")
	public Object maxDate() {
		return reportObstacleClient.maxDate();
	}
	
	@RequestMapping(value = "/obstacle_user_declaration_list/download", method = RequestMethod.POST)
    public void obstacleExportExcel(@RequestParam Map<String, Object> map,HttpServletResponse response) throws IOException {
		ResponseEntity<byte[]> entity = downClient.obstacleExportExcel(map);
		String filename = entity.getHeaders().getFirst("content-disposition").split("filename=")[1];
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        
        InputStream is = new ByteArrayInputStream(entity.getBody());
        org.apache.tomcat.util.http.fileupload.IOUtils.copy(is, response.getOutputStream());
	}
	

}
