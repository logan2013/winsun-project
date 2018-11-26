package com.cloud.app.fallback;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${winsum.admin.serviceId}")
public interface WinsunDownFeginClient {
	
	@RequestMapping(value = "/ciop/compared/meal/exportExcel", method = RequestMethod.POST)
	public ResponseEntity<byte[]> exportExcel();

}
