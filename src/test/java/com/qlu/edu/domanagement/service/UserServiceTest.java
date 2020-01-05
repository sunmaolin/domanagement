package com.qlu.edu.domanagement.service;

import com.qlu.edu.domanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void login(){
        try{
            User user=new User();
            user.setUsername("su");
            user.setPassword("s");
            User loginUser=userService.login(user);
            System.err.println(loginUser);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
