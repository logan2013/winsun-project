package com.cloud.app.service;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.app.fallback.WinsunKaptchaFeginClientFallback;


@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunKaptchaFeginClientFallback.class)
public interface WinsunKaptchaFeginClient {
	
	@RequestMapping("/ciop/kaptcha")
	public void index();
	
	@RequestMapping("/ciop/kaptcha/{pictureId}")
	public void renderPicture(@PathVariable("pictureId") String pictureId);

}
