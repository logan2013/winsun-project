package com.cloud.app.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.app.admin.service.WinsunKaptchaFeginClient;

@Controller
public class FeginkaptchaController {
	
	@Autowired
	WinsunKaptchaFeginClient client;
	
	@RequestMapping("/kaptcha")
	public void index() {
		client.index();
	}
	
	@RequestMapping("/kaptcha/{pictureId}")
	public void renderPicture(@PathVariable("pictureId") String pictureId) {
		client.renderPicture(pictureId);
	}
	

}
