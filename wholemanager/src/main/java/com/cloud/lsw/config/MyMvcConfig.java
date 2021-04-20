package com.cloud.lsw.config;

import com.cloud.lsw.controller.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 实现登录拦截
 * @author lisw
 * @create 2021/4/19 22:15
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").
                addResourceLocations("classpath:/static/**");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //todo 拦截器静态资源无法通过，404
        registry.addInterceptor(loginHandlerInterceptor).addPathPatterns("/**")
                .excludePathPatterns(loginHandlerInterceptor.getUrls());
    }
}
