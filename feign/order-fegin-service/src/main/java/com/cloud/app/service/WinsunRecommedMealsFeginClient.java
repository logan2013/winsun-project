package com.cloud.app.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloud.app.fallback.WinsunRecommedMealsFeginClientFallback;
import com.cloud.app.model.DimActDisc;


@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunRecommedMealsFeginClientFallback.class)
public interface WinsunRecommedMealsFeginClient {
	
	@RequestMapping(value = "/ciop/compared/meal/list",method = RequestMethod.POST)
	public Object list(@RequestParam Map<String, Object> map);
	
	@RequestMapping(value = "/ciop/compared/meal/add")
	public Object add(@RequestParam Map<String, Object> dimActDisc);
	
	@RequestMapping(value = "/ciop/compared/meal/detail/{id}",method = RequestMethod.GET)
	public Object detail(@PathVariable Integer id);
	
	@RequestMapping(value = "/ciop/compared/meal/update")
    public Object update(@RequestParam Map<String, Object> dimActDisc);
	
	@RequestMapping(value = "/ciop/compared/meal/delete",method = RequestMethod.POST)
    public Object delete(@RequestParam String id);
	
	
}
