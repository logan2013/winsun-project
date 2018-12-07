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

import com.cloud.app.report.service.ReportBroadbandListFeginClient;
import com.cloud.app.report.service.ReportDownFeginClient;

@RestController
public class ReportBroadbandListController {
	
	@Autowired
	ReportBroadbandListFeginClient rportBroadbandListClient;
	
	@Autowired
	ReportDownFeginClient downClient;
	
	@RequestMapping(value = "/free_trial_broadband_list/list")
	public Object getDataList(@RequestParam Map<String,Object> dept) {
		return rportBroadbandListClient.getDataList(dept);
	}
	
	@RequestMapping(value = "/free_trial_broadband_list/maxDate")
	public Object maxDate() {
		return rportBroadbandListClient.maxDate();
	}
	
	@RequestMapping(value = "/free_trial_broadband_list/download", method = RequestMethod.POST)
	public void installExportExcel(@RequestParam Map<String, Object> map,HttpServletResponse response) throws IOException {
		ResponseEntity<byte[]> entity = downClient.broadbandExportExcel(map);
		String filename = entity.getHeaders().getFirst("content-disposition").split("filename=")[1];
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        
        InputStream is = new ByteArrayInputStream(entity.getBody());
        org.apache.tomcat.util.http.fileupload.IOUtils.copy(is, response.getOutputStream());
	}
	
	
	

}
