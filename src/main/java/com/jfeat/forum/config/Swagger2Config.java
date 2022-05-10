package com.jfeat.forum.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置Swagger进行接口的测试开发
 * @Author 林学文
 * @Date 2022/1/17 11:49
 * @Version 1.0
 */
@Configuration
@EnableSwagger2

public class Swagger2Config {
        @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.gzhh"))
                .paths(PathSelectors.any())
                .build();
    }
//
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("码农之家论坛api文档")
                .description("码农之家论坛api文档")
                .termsOfServiceUrl("http://localhost:8090")
                .version("1.0")
                .build();
    }
}
