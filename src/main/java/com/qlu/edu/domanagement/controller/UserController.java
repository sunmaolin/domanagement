package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.entity.User;
import com.qlu.edu.domanagement.service.UserService;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 处理管理员用户数据的相关请求控制器类
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public JsonResult login(User user, HttpSession session){
        User loginUser=userService.login(user);
        session.setAttribute("username",loginUser.getUsername());
        session.setAttribute("uid",loginUser.getUid());
        session.setAttribute("su",loginUser.getSu());
        return new JsonResult(OK);
    }

    @PostMapping("change_password")
    public JsonResult changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid=(Integer)session.getAttribute("uid");
        userService.changePassword(uid,oldPassword,newPassword);
        session.invalidate();//移除所有数据
        return new JsonResult(OK);
    }

    @PostMapping("is_su")
    public JsonResult isSu(HttpSession session){
        Integer uid=(Integer)session.getAttribute("uid");
        userService.isSu(uid);
        return new JsonResult(OK);
    }

    @PostMapping("clear_session")
    public void clearSession(HttpSession session){
        session.invalidate();//退出登陆移除所有session数据
    }
}
