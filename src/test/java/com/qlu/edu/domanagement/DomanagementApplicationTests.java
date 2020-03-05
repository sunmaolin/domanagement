package com.qlu.edu.domanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class DomanagementApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        Connection conn=dataSource.getConnection();
        System.out.println(conn);
    }

    @Test
    void reflectTest(){
        Class dis= null;
        try {
            dis = Class.forName("com.qlu.edu.domanagement.entity.Disciplinary");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Field[] fields = dis.getDeclaredFields();
        for (Field field:fields){
            System.out.println(field.getName());
        }
    }



}
