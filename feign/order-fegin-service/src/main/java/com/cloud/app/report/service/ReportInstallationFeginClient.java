package com.cloud.app.report.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.report.fallback.ReportBroadbandListFeginClientFallback;
import com.cloud.app.report.fallback.ReportInstallationFeginClientFallback;

@FeignClient(value = "${winsum.report.serviceId}",fallback = ReportInstallationFeginClientFallback.class)
public interface ReportInstallationFeginClient {
	
	@RequestMapping(value = "/ciop/installation_maintenance_information/list")
	public Object getDataList(@RequestParam Map<String, Object> map);
	
	@RequestMapping(value = "/ciop/installation_maintenance_information/maxDate")
	public Object maxDate();
	
	@RequestMapping(value = "/ciop/installation_maintenance_information/pq/list")
	public Object getPQDataList(@RequestParam Map<String, Object> map);
	
	@RequestMapping(value = "/ciop/installation_maintenance_information/pq/maxDate")
	public Object maxPQDate();
	
}
