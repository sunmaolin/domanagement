package com.qlu.edu.domanagement.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截未登录的请求的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("username") == null){
            //ajax请求处理
            if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                response.addHeader("NOLOGIN","true");
            //普通处理
            }else{
                response.sendRedirect("/web/login.html");
            }
            return false;
        }
        return true;
    }
}
