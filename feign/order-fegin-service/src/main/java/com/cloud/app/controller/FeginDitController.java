package com.cloud.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloud.app.service.WinsunDictFeginClient;

@Controller
public class FeginDitController {
	
	@Autowired
	WinsunDictFeginClient client;

	@RequestMapping("/dict")
	public String index() {
		return client.index();
	}
	
	@RequestMapping("/dict/dict_add")
	public String deptAdd() {
		return client.deptAdd();
	}
	
	@RequestMapping("/dict/dict_edit/{dictId}")
	public String deptUpdate(@PathVariable Integer dictId) {
		return client.deptUpdate(dictId);
	}
	
	@RequestMapping(value = "/dict/add")
	@ResponseBody
	public Object add(@RequestParam("dictCode")String dictCode,
					  @RequestParam("dictTips")String dictTips,
					  @RequestParam("dictName")String dictName, 
					  @RequestParam("dictValues")String dictValues) {
		return client.add(dictCode, dictTips, dictName, dictValues);
	}

	@RequestMapping(value = "/dict/list")
	@ResponseBody
	public Object list(@RequestParam("condition")String condition) {
		return client.list(condition);
	}
	
	@RequestMapping(value = "/dict/detail/{dictId}")
	@ResponseBody
	public Object detail(@PathVariable("dictId") Integer dictId) {
		return client.detail(dictId);
	}
	
	@RequestMapping(value = "/dict/update")
	@ResponseBody
	public Object update(@RequestParam("dictCode")Integer dictId,
						 @RequestParam("dictCode")String dictCode,
						 @RequestParam("dictCode")String dictName,
						 @RequestParam("dictCode")String dictTips,
						 @RequestParam("dictCode")String dictValues) {
		return client.update(dictId, dictCode, dictName, dictTips, dictValues);
	}
	
	@RequestMapping(value = "/dict/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer dictId) {
		return client.delete(dictId);
	}
	
}
