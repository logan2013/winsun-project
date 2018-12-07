package com.cloud.app.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.report.service.ReportDownFeginClient;
import com.cloud.app.report.service.ReportInstallationFeginClient;

@RestController
public class ReportInstallationController {
	
	@Autowired
	ReportInstallationFeginClient reportInstallClient;
	
	@Autowired
	ReportDownFeginClient downClient;
	
	@RequestMapping(value = "/installation_maintenance_information/list")
	public Object getDataList(@RequestParam Map<String, Object> map) {
		return reportInstallClient.getDataList(map);
	}
	
	@RequestMapping(value = "/installation_maintenance_information/maxDate")
	public Object maxDate() {
		return reportInstallClient.maxDate();
	}
	
	@RequestMapping(value = "/installation_maintenance_information/pq/list")
	public Object getPQDataList(@RequestParam Map<String, Object> map) {
		return reportInstallClient.getPQDataList(map);
	}
	
	@RequestMapping(value = "/installation_maintenance_information/pq/maxDate")
	public Object maxPQDate() {
		return reportInstallClient.maxPQDate();
	}
	
	@RequestMapping(value = "/installation_maintenance_information/download", method = RequestMethod.POST)
    public void installExportExcel(@RequestParam Map<String, Object> map,HttpServletResponse response) throws IOException {
		ResponseEntity<byte[]> entity = downClient.installExportExcel(map);
		String filename = entity.getHeaders().getFirst("content-disposition").split("filename=")[1];
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        
        InputStream is = new ByteArrayInputStream(entity.getBody());
        org.apache.tomcat.util.http.fileupload.IOUtils.copy(is, response.getOutputStream());
	}

	@RequestMapping(value = "/installation_maintenance_information/pq/download", method = RequestMethod.POST)
	public void installExportExcelPQ(@RequestParam Map<String, Object> map,HttpServletResponse response) throws IOException {
		ResponseEntity<byte[]> entity = downClient.installExportExcelPQ(map);
		String filename = entity.getHeaders().getFirst("content-disposition").split("filename=")[1];
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        
        InputStream is = new ByteArrayInputStream(entity.getBody());
        org.apache.tomcat.util.http.fileupload.IOUtils.copy(is, response.getOutputStream());
	}
}
