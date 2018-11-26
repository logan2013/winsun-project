package com.cloud.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.app.fallback.WinsunRecommedMealsFeginClientFallback;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(value = "${winsum.admin.serviceId}",configuration = WinsunUploadFeginClient.MultipartSupportConfig.class)
public interface WinsunUploadFeginClient {
	
	@Scope("prototype")
    @Primary
    @Configuration
	class MultipartSupportConfig {
		@Autowired  
	    private ObjectFactory<HttpMessageConverters> messageConverters;  
	          
	    @Bean  
	    public Encoder feignFormEncoder() {  
	        return new SpringFormEncoder(new SpringEncoder(messageConverters));  
	    }  
	
	}

	
	@RequestMapping(value = "/ciop/compared/meal/importExcel", method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Object importExcel(@RequestPart("file") MultipartFile multipartFile);
	
	@RequestMapping(value = "/ciop/mgr/upload",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String upload(@RequestPart("file") MultipartFile picture);
	
}
