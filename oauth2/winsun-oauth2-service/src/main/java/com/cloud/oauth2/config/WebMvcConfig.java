package com.cloud.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cloud.oauth2.interceptor.URLInterceptor;

@Component
public class WebMvcConfig implements WebMvcConfigurer{

	@Bean
	URLInterceptor URLInterceptor() {
		return new URLInterceptor();
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(URLInterceptor()).addPathPatterns("/oauth/**");
    }

	
}
