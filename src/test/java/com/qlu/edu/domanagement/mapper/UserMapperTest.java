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
    public void findByUsername(){
        String username="s";
        Integer rows=userMapper.findByUsername(username);
        System.out.println(rows);
    }

    @Test
    public void login(){
        User user=new User();
        user.setUsername("su");
        user.setPassword("su");
        User a=userMapper.login(user);
        System.out.println(a.getUsername());
    }
}
