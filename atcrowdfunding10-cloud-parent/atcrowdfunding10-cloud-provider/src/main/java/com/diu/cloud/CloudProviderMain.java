package com.diu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author DIU
 * @date 2021/11/13 13:21
 */
@EnableEurekaClient
@SpringBootApplication
public class CloudProviderMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CloudProviderMain.class, args);
    }

}
