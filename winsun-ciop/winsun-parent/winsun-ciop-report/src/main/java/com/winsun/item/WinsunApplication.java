package com.winsun.item;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

/**
 * SpringBoot方式启动类
 *
 * @author winsun
 * @Date 2017/5/21 12:06
 */
@EnableEurekaClient
@SpringBootApplication
@PropertySource(value = "file:${user.dir}/config/bootstrap.properties")
public class WinsunApplication {

    private final static Logger logger = LoggerFactory.getLogger(WinsunApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WinsunApplication.class, args);
        logger.info("WinsunApplication is success!");
    }
}
