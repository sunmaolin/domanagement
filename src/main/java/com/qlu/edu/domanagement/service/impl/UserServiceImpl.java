package com.qlu.edu.domanagement.service.impl;

import com.qlu.edu.domanagement.entity.User;
import com.qlu.edu.domanagement.mapper.UserMapper;
import com.qlu.edu.domanagement.service.UserService;
import com.qlu.edu.domanagement.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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

    @Override
    public User[] findUser(Integer uid) {
        return userMapper.findUser(uid);
    }

    @Override
    public void deleteUser(Integer uid, String username) {
        Integer row=userMapper.deleteUser(uid, username);
        if(row!=1){
            throw new ServiceException("删除失败！请联系开发人员！");
        }
    }

    @Override
    public void findUsername(String username) {
        Integer rows=userMapper.findUserName(username);
        if(rows!=0){
            throw new UserNameIsHavaException("用户名已存在！");
        }

    }

    @Override
    public void addUser(String username, String password) {
        Integer rows=userMapper.addUser(username,password);
        if(rows!=1){
            throw new ServiceException("增加失败，未知原因请联系开发人员！");
        }
    }
}
