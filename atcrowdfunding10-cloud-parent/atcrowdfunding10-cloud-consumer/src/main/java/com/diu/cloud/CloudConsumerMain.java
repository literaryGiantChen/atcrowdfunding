package com.diu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author DIU
 * @date 2021/11/13 13:19
 */
// @EnableDiscoveryClient 和 @EnableEurekaClient 使用效果一致，唯一区别是@EnableDiscoveryClient在其他注册中心也可以使用，而@EnableEurekaClient只能在eureka中心使用
@EnableEurekaClient
@SpringBootApplication
public class CloudConsumerMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(CloudConsumerMain.class, args);
    }

}
