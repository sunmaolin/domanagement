package com.qlu.edu.domanagement.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FloorAndDormitoryTest {
    @Autowired
    DormitoryService dormitoryService;

    @Test
    public void findFloorAndDormitory(){
        System.err.println(dormitoryService.findFloorAndDormitory(0,null).toString());
    }
}
