package com.diu.boot;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan(value = "com.diu.boot.mapper")
@SpringBootApplication
@Slf4j
public class Atcrowdfunding09BootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Atcrowdfunding09BootApplication.class, args);
        log.info("初始化Bean一共有：{}", run.getBeanDefinitionCount());
    }

}
