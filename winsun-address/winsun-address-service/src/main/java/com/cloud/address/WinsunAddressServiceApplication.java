package com.cloud.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WinsunAddressServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(WinsunAddressServiceApplication.class, args);
	}
}