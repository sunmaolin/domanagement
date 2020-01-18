package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.Dormitory;
import com.qlu.edu.domanagement.entity.Floor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FloorAndDormitoryMapperTest {

    @Autowired
    DormitoryMapper dormitoryMapper;

    @Test
    public void findFloor(){
        Floor[] floors= dormitoryMapper.findFloor();
        for (Floor floor:floors){
            System.err.println(floor.toString());
        }
    }

    @Test
    public void findDormitory(){
        Dormitory[] dormitories= dormitoryMapper.findDormitoryByFid(1);
        System.err.println(dormitories.toString());
        System.err.println(dormitories.length);

        for (Dormitory dormitory:dormitories){
            System.err.println(dormitory.toString());
        }
    }
}
