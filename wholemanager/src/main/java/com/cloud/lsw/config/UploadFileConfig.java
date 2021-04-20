package com.cloud.lsw.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

/**
 * 文件上传的配置类
 * @author lisw
 * @create 2021/4/18 15:44
 */
@Configuration
public class UploadFileConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大
        factory.setMaxFileSize(DataSize.of(12L, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }
}
