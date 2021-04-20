package com.cloud.lsw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lisw
 * @create 2021/3/24 19:45
 */
@Controller
public class MyErrorPageController {

    @RequestMapping("404")
    public String toPage404(){
        return "error/404";
    }
    @RequestMapping("500")
    public String toPage500(){
        return "error/500";
    }

}
