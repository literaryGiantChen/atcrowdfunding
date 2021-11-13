package com.diu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author DIU
 * @date 2021/11/13 14:49
 */
@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CloudEurekaMain.class, args);

    }

}
