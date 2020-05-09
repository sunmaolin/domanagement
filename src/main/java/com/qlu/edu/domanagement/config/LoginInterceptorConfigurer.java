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
        //学生端查找学生
        patterns.add("/student/find/{did}");
        //学生端报修
        patterns.add("/dormitory/addMaintainRecord");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("favicon.ico");
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //获取文件的真实路径 work_project代表项目工程名 需要更改
//        String path = System.getProperty("user.dir")+"\\domanagement\\src\\main\\resources\\static\\images\\upload";
//        String os = System.getProperty("os.name");
////        if (os.toLowerCase().startsWith("win")) {
////            registry.addResourceHandler("/images/upload/**").addResourceLocations("file:"+path);
////        }else{//linux和mac系统 可以根据逻辑再做处理
////            registry.addResourceHandler("/images/upload/**").addResourceLocations("file:"+path);
////        }
////        addResourceHandlers(registry);
//    }

}
