package com.cloud.lsw.service.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 所有service的基类
 * @author lisw
 * @create 2021/4/11 17:10
 */
public class BaseService {

    public String getLoginUserName(HttpServletRequest request){
        HttpSession session = request.getSession();

        return (String) session.getAttribute("username");
    }
}
