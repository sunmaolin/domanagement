package com.qlu.edu.domanagement.service.impl;

import com.qlu.edu.domanagement.entity.User;
import com.qlu.edu.domanagement.mapper.UserMapper;
import com.qlu.edu.domanagement.service.UserService;
import com.qlu.edu.domanagement.service.ex.PasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User loginUser=userMapper.login(user);
        if(loginUser==null){
            throw new PasswordNotMatchException("账号或密码错误！");
        }
        return loginUser;
    }

}
