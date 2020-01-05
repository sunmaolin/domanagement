package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.entity.User;
import com.qlu.edu.domanagement.service.UserService;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理管理员用户数据的相关请求控制器类
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @PostMapping("login")
    public JsonResult<Void> login(User user){
        User loginUser=userService.login(user);
        //TODO 存储session
        return new JsonResult(OK);
    }
}
