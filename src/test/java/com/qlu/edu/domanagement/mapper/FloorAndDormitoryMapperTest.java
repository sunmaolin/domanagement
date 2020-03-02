package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void insertFloorOrDormitory(){
        Integer row=dormitoryMapper.insertFloor("五号楼");
        Integer rows=dormitoryMapper.insertDormitory(5,"101");
        System.err.println(row+"------"+rows);
    }

    @Test
    public void findFloorName(){
        String fname=dormitoryMapper.findFloorName("一楼");
        System.err.println(fname==null);
    }

    @Test
    public void findDormitoryName(){
        String dname=dormitoryMapper.findDormitoryName(1,"101");
        System.err.println(dname);
    }

    @Test
    public void findChildren(){
        Integer children=dormitoryMapper.findChildren("一号楼");
        System.err.println(children);
    }

    @Test
    public void findDormitoryDisciplinary(){
        Disciplinary[] disciplinaries = dormitoryMapper.findDormitoryDisciplinary(26);
        for (int i = 0; i < disciplinaries.length; i++) {
            System.err.println(disciplinaries[i].toString());
        }
    }

    @Test
    public void findDormitoryMaintain(){
        Maintain[] maintains=dormitoryMapper.findDormitoryMaintain(26);
        System.err.println(maintains[0].toString());
    }

    @Test
    public void findAllDormitoryDiscrlinary(){
        Disciplinary[] disciplinaries=dormitoryMapper.findAllDormitoryDisciplinary();
        for (int i=0;i<disciplinaries.length;i++){
            System.out.println(disciplinaries[i].toString());
        }
    }

    @Test
    public void findAllNotice(){
        Notice[] notices = dormitoryMapper.findAllNotice();
        System.out.println(notices[0].toString());
    }



}
