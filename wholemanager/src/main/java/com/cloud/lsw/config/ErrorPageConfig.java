package com.cloud.lsw.config;

import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author lisw
 * @create 2021/3/24 17:11
 * 全局配置错误页面
 */
@Configuration
public class ErrorPageConfig {

    /**
     * 以编程方式配置嵌入式servlet容器，可以通过注册实现该 WebServerFactoryCustomizer 接口的Spring bean
     * TomcatServletWebServerFactory，JettyServletWebServerFactory并且UndertowServletWebServerFactory 是专用变体，
     ConfigurableServletWebServerFactory分别为Tomcat，Jetty和Undertow提供了额外的自定义setter方法。
     * @return
     */
//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                // 对嵌入式servlet容器的配置
//                /* 注意：new ErrorPage(stat, path);中path必须是页面名称，并且必须“/”开始。
//                    底层调用了String.java中如下方法：
//                    public boolean startsWith(String prefix) {
//                        return startsWith(prefix, 0);
//                    }*/
//                ErrorPage errorPage400 = new ErrorPage(HttpStatus.BAD_REQUEST,
//                        "/400");
//                ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,
//                        "/404");
//                ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
//                        "/500");
//                factory.addErrorPages(errorPage400, errorPage404,
//                        errorPage500);
//            }
//        };
//    }

}
