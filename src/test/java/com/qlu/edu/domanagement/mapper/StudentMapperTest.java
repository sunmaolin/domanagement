package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.RandomDuty;
import com.qlu.edu.domanagement.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void findStudentsByDid(){

        Student[] students = studentMapper.findStudentsByDid(26);
        for (int i=0;i<students.length;i++){
            System.out.println(students[i].toString());
        }

    }

    @Test
    public void insertRandomDuty(){
        RandomDuty randomDuty=new RandomDuty();
        randomDuty.setDid(1);
        randomDuty.setMonday("孙");
        randomDuty.setTuesday("毛");
        randomDuty.setWednesday("Lin");
        randomDuty.setThursday("ss");
        randomDuty.setFriday("sssss");
        randomDuty.setSaturday("scudhcsu");
        randomDuty.setSunday("shudshcusd");
        studentMapper.insertRandomDuty(randomDuty);
    }
}
