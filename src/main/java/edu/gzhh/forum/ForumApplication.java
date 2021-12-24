package edu.gzhh.forum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

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
public class ForumApplication {
    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class,args);
    }
}
