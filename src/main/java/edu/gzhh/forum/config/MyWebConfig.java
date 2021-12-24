package edu.gzhh.forum.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author 林学文
 * @Date 2021/12/16 17:26
 * @Version 1.0
 */
@Configuration
public class MyWebConfig extends WebMvcConfigurationSupport {
    /*静态资源配置*/
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates/")
                .addResourceLocations("classpath:/upload/");
    }
/*页面跳转*/
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }
/*拦截器配置*/
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }
}
