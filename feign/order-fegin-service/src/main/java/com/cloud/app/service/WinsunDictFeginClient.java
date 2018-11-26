package com.cloud.app.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloud.app.fallback.WinsunDictFeginClientFallback;


@FeignClient(value = "${winsum.admin.serviceId}",fallback = WinsunDictFeginClientFallback.class)
public interface WinsunDictFeginClient {
	
	@RequestMapping("/ciop/dict")
	public String index();
	
	@RequestMapping("/ciop/dict/dict_add")
	public String deptAdd();
	
	@RequestMapping("/ciop/dict/dict_edit/{dictId}")
	public String deptUpdate(@PathVariable Integer dictId);
	
	@RequestMapping(value = "/ciop/dict/add")
	public Object add(@RequestParam("dictCode")String dictCode,
					  @RequestParam("dictTips")String dictTips,
					  @RequestParam("dictName")String dictName, 
					  @RequestParam("dictValues")String dictValues);

	@RequestMapping(value = "/ciop/dict/list")
	public Object list(@RequestParam("condition")String condition);
	
	@RequestMapping(value = "/ciop/dict/detail/{dictId}")
	public Object detail(@PathVariable("dictId") Integer dictId);
	
	@RequestMapping(value = "/ciop/dict/update")
	public Object update(@RequestParam("dictCode")Integer dictId,
						 @RequestParam("dictCode")String dictCode,
						 @RequestParam("dictCode")String dictName,
						 @RequestParam("dictCode")String dictTips,
						 @RequestParam("dictCode")String dictValues);
	
	@RequestMapping(value = "/ciop/dict/delete")
	public Object delete(@RequestParam Integer dictId);
}
