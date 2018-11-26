package com.cloud.app.fallback;

import org.springframework.stereotype.Component;

import com.cloud.app.service.WinsunKaptchaFeginClient;

@Component
public class WinsunKaptchaFeginClientFallback implements WinsunKaptchaFeginClient{

	@Override
	public void index() {
		
	}

	@Override
	public void renderPicture(String pictureId) {
		
	}

}
