package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void login(){
        User user=new User();
        user.setUsername("su");
        user.setPassword("su");
        User a=userMapper.login(user);
        System.out.println(a.getUsername());
    }

    @Test
    public void changePassword(){
        String oldPassword="su";
        String newPassword="sml";
        Integer row=userMapper.changePassword(1,oldPassword,newPassword);
        System.err.println(row);
    }

    @Test
    public  void findUser(){
        User[] user=userMapper.findUser(1);
        System.err.println(user);
    }

    @Test
    public void  findUserName(){
        Integer row=userMapper.findUserName("sss");
        System.err.println(row);
    }

    @Test
    public void deleteUser(){
        Integer row=userMapper.deleteUser(3,"sml");
        System.err.println(row);
    }

    @Test
    public void addUser(){
        Integer rows=userMapper.addUser("bb","bb");
        System.err.println(rows);
    }
}
