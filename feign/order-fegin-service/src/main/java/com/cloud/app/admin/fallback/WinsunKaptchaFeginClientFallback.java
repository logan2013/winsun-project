package com.cloud.app.admin.fallback;

import org.springframework.stereotype.Component;

import com.cloud.app.admin.service.WinsunKaptchaFeginClient;

@Component
public class WinsunKaptchaFeginClientFallback implements WinsunKaptchaFeginClient{

	@Override
	public void index() {
		
	}

	@Override
	public void renderPicture(String pictureId) {
		
	}

}
