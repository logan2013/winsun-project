package com.cloud.app.report.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.report.fallback.ReportBroadbandListFeginClientFallback;
import com.cloud.app.report.fallback.ReportObstacleFeginClientFallback;


@FeignClient(value = "${winsum.report.serviceId}",fallback = ReportObstacleFeginClientFallback.class)
public interface ReportObstacleFeginClient {

	@RequestMapping(value="/ciop/obstacle_user_declaration_list/list")
	public Object getDataList(@RequestParam Map<String, Object> map);
	
	@RequestMapping(value = "/ciop/obstacle_user_declaration_list/maxDate")
	public Object maxDate();
	
}
