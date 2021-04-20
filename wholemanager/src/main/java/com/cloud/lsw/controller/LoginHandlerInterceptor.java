package com.cloud.lsw.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.scene.effect.SepiaTone;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lisw
 * @create 2021/4/19 22:18
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**用于存储排除拦截的URl*/
    private List<String> list = new ArrayList<>();

    /**默认的响应流字符编码*/
    private String defaultCharset = "UTF-8";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        String contentType = request.getHeader("Accept");

        if (username == null){
            response.setContentType(contentType == null ? "text/html;charset=" + defaultCharset :
                    contentType + ";charset=" + defaultCharset);
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    public List<String> getUrls(){
        list.add("/");
        list.add("/html/logout");
        list.add("/html/loginValid");
        list.add("/html/register/valid");
        list.add("/error/**");
        list.add("/html/index");
        list.add("/html/register");
        list.add("/webjars/**");
        list.add("/static/**");

//         /**静态资源*/
        list.add("/images/**");
        list.add("/css/**");
        list.add("/js/**");
        list.add("/plugins/**");
        return list;
    }
}
