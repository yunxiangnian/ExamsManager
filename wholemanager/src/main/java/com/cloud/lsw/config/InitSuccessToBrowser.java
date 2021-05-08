package com.cloud.lsw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author lisw
 * @create 2021/5/7 12:05
 */
@Component
public class InitSuccessToBrowser implements CommandLineRunner {

    @Value("${init.port}")
    private String port;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序初始化完成...即将打开指定页面");
        try{
            Runtime.getRuntime().exec("cmd /c start http://localhost:" + port);
        }catch (Exception e){
            throw  new RuntimeException("出错了",e);
        }
    }
}
