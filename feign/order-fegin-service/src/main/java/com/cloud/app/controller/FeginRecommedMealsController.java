package com.cloud.app.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.util.IOUtils;
import com.cloud.app.fallback.WinsunDownFeginClient;
import com.cloud.app.model.DimActDisc;
import com.cloud.app.service.WinsunRecommedMealsFeginClient;
import com.cloud.app.service.WinsunUploadFeginClient;



@RestController
public class FeginRecommedMealsController {
	
	@Autowired
	WinsunRecommedMealsFeginClient client;
	
	@Autowired
	WinsunUploadFeginClient uploadClient;
	
	@Autowired
	WinsunDownFeginClient downClient;

	@RequestMapping(value = "/compared/meal/list")
	public Object list(@RequestParam Map<String, Object> map) {
		return client.list(map);
	}
	
	@RequestMapping(value = "/compared/meal/add")
	public Object add(@RequestParam Map<String,Object> dimActDisc) {
		return client.add(dimActDisc);
	}
	
	@RequestMapping(value = "/compared/meal/detail/{id}")
	public Object detail(@PathVariable Integer id) {
		return client.detail(id);
	}
	
	@RequestMapping(value = "/compared/meal/update")
    public Object update(@RequestParam Map<String,Object> dimActDisc) {
		return client.update(dimActDisc);
	}
	
	@RequestMapping(value = "/compared/meal/delete")
    public Object delete(@RequestParam String id) {
		return client.delete(id);
	}
	
	@RequestMapping(value = "/compared/meal/exportExcel", method = RequestMethod.POST)
	public void exportExcel(HttpServletResponse response) throws IOException {
		ResponseEntity<byte[]> exportExcel = downClient.exportExcel();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=exportData.xlsx");
        
        byte[] body = exportExcel.getBody();
        InputStream is = new ByteArrayInputStream(body);
        org.apache.tomcat.util.http.fileupload.IOUtils.copy(is, response.getOutputStream());
	}
	
	@RequestMapping(value = "/compared/meal/importExcel", method = RequestMethod.POST)
	public Object importExcel(@RequestParam("file") MultipartFile multipartFile) {
		return uploadClient.importExcel(multipartFile);
	}
	
}
