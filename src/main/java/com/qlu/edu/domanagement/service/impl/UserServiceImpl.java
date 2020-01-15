package com.qlu.edu.domanagement.service.impl;

import com.qlu.edu.domanagement.entity.User;
import com.qlu.edu.domanagement.mapper.UserMapper;
import com.qlu.edu.domanagement.service.UserService;
import com.qlu.edu.domanagement.service.ex.NotIsSuException;
import com.qlu.edu.domanagement.service.ex.PasswordNotMatchException;
import com.qlu.edu.domanagement.service.ex.oldPasswordNotMatchException;
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

    @Override
    public void isSu(Integer uid) {
        Integer su=userMapper.isSu(uid);
        if(su==0){
            throw new NotIsSuException("你无权操作！");
        }
    }

    @Override
    public void changePassword(Integer uid, String oldPassword, String newPassword) {
        Integer changeRow=userMapper.changePassword(uid, oldPassword, newPassword);
        if(changeRow != 1){
            throw new oldPasswordNotMatchException("当前密码错误！");
        }
    }
}
