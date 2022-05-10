package com.jfeat;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SpringBoot CG Test Application
 *
 * @author Admin
 * @Date 2017/5/21 12:06
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
public class AmApplication extends WebMvcConfigurerAdapter {

   private static Logger log = LoggerFactory.getLogger(AmApplication.class.getSimpleName());
   
     /**
      * 增加swagger的支持
      */
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
         registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        
         registry.addResourceHandler("dependency-ui.html").addResourceLocations("classpath:/META-INF/resources/");
         registry.addResourceHandler("/dependency-ui/**").addResourceLocations("classpath:/META-INF/resources/dependency-ui/");
     }

    public static void main(String[] args) {
        SpringApplication.run(AmApplication.class, args);
        log.info("NFT-ARTIFACT-UAT standalone run success!");
    }
}
