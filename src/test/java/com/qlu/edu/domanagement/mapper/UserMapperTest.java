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
}
