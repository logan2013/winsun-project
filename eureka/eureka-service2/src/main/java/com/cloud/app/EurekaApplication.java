package com.cloud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.PropertySource;
@SpringBootApplication
@EnableEurekaServer
@PropertySource(value = "file:${user.dir}/config/application.yml")
public class EurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args);
	}
	
}
