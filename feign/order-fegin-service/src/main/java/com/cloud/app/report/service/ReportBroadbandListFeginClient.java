package com.cloud.app.report.service;

import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.report.fallback.ReportBroadbandListFeginClientFallback;



@FeignClient(value = "${winsum.report.serviceId}",fallback = ReportBroadbandListFeginClientFallback.class)
public interface ReportBroadbandListFeginClient {
	
	@RequestMapping(value = "/ciop/free_trial_broadband_list/list")
	public Object getDataList(@RequestParam Map<String,Object> dept);
	
	@RequestMapping(value = "/ciop/free_trial_broadband_list/maxDate")
	public Object maxDate();
	
	
}
