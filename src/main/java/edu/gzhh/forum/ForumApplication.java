package edu.gzhh.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 林学文
 * @Date 2021/12/6 20:22
 * @Version 1.0
 */

@SpringBootApplication
@EnableScheduling//定时任务
@MapperScan("edu.gzhh.forum.mapper")
@ComponentScan(basePackages = {"edu.gzhh.forum.service.impl"})
@ComponentScan(basePackages = {"edu.gzhh"})
@ComponentScan(basePackages = {"edu.gzhh.forum.config"})
@ComponentScan(basePackages = {"edu.gzhh.forum.interceptor"})
@ServletComponentScan("edu.gzhh.forum.config")
@EnableSwagger2
public class ForumApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class,args);
    }
}
