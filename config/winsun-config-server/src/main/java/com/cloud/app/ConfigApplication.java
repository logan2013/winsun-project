package com.cloud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigServer
@PropertySource(value = "file:${user.dir}/config/application.properties")
public class ConfigApplication {

	    public static void main(String[] args) {
	        SpringApplication.run(ConfigApplication.class, args);
	    }
	
}
