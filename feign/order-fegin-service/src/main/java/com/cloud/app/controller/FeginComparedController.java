package com.cloud.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.app.service.WinsunComparedFeginClient;

@RestController
public class FeginComparedController {
	
	@Autowired
	WinsunComparedFeginClient client;
	
	@RequestMapping(value = "/compared/comparison")
	public Object compared(@RequestParam Map<String,Object> map) {
		return client.compared(map);
	}
	
	@RequestMapping(value = "/compared/test")
	public Object test() {
		return client.test();
	}
	
	@RequestMapping(value = "/compared/agree")
	public Object agreeResult(@RequestParam("id")String id,@RequestParam("speed")String speed) {
		return client.agreeResult(id,speed);
	}
	
	@RequestMapping(value = "/compared/record/list")
	public Object recordList(@RequestParam Map<String,Object> map) {
		return client.recordList(map);
	}
	
	@RequestMapping(value = "/compared/record/pc/list")
	public Object recordList2(@RequestParam Map<String,Object> map) {
		return client.recordList2(map);
	}
	
	@RequestMapping(value = "/compared/record/detail/{id}")
	public Object recordDetail(@PathVariable String id) {
		return client.recordDetail(id);
	}

}
