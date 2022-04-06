package edu.gzhh.forum.config;

import edu.gzhh.forum.common.Constants;
import edu.gzhh.forum.interceptor.adminLoginInterceptor;
import edu.gzhh.forum.interceptor.userLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    adminLoginInterceptor adminLoginInterceptor;
    @Autowired
    userLoginInterceptor userLoginInterceptor;


    /*静态资源配置*/
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("dependency-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/dependency-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/dependency-ui/");

        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/upload/**/").addResourceLocations("file:"+ Constants.FILE_UPLOAD_DIC);
    }
/*页面跳转*/
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
    }
/*拦截器配置*/
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginInterceptor);
        registry.addInterceptor(userLoginInterceptor);
        super.addInterceptors(registry);
    }
}
