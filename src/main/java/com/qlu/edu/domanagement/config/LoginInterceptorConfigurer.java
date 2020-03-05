package com.qlu.edu.domanagement.config;

import com.qlu.edu.domanagement.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器的配置
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor=new LoginInterceptor();
        List<String> patterns=new ArrayList<>();
        patterns.add("/web/login.html");
        patterns.add("/users/login");
        //学生端获取通知信息
        patterns.add("/dormitory/findAllNotice");
        //学生端进行留言
        patterns.add("/student/addMessage");
        //学生端查找宿舍楼
        patterns.add("/dormitory/findAllFloor");
        //学生端查找宿舍
        patterns.add("/dormitory/findDormitoryByFid");
        //学生端查找值日表
        patterns.add("/student/findRandomDuty/{did}");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("favicon.ico");
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }
}
