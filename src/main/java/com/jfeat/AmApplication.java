package com.jfeat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableScheduling//定时任务
@EnableSwagger2
//@ComponentScan(basePackages = {"com.jfeat.forum.config"})
public class AmApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmApplication.class, args);
        log.info("Application run success!");
    }
}