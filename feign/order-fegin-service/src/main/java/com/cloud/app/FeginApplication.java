package com.cloud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@PropertySource(value = "file:${user.dir}/config/bootstrap.properties")
public class FeginApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(FeginApplication.class, args);
	}

}
