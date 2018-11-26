package com.cloud.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource(value = "file:${user.dir}/config/application.properties")
public class WinsunOauth2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WinsunOauth2ServiceApplication.class, args);
	}
}
