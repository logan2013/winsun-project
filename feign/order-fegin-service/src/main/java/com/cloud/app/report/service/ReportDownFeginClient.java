package com.cloud.app.report.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.report.fallback.ReportDownFeginClientFallback;

@FeignClient(value = "${winsum.report.serviceId}",fallback = ReportDownFeginClientFallback.class)
public interface ReportDownFeginClient {
	
	@RequestMapping(value = "/ciop/free_trial_broadband_list/download", method = RequestMethod.POST)
	public ResponseEntity<byte[]> broadbandExportExcel(@RequestParam Map<String, Object> map);
	
	@RequestMapping(value = "/ciop/installation_maintenance_information/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> installExportExcel(@RequestParam Map<String, Object> map);
    
    @RequestMapping(value = "/ciop/installation_maintenance_information/pq/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> installExportExcelPQ(@RequestParam Map<String, Object> map);
    
    @RequestMapping(value = "/ciop/obstacle_user_declaration_list/download", method = RequestMethod.POST)
    public ResponseEntity<byte[]> obstacleExportExcel(@RequestParam Map<String, Object> map);

}
