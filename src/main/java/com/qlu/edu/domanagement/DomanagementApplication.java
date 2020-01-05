package com.qlu.edu.domanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qlu.edu.domanagement.mapper")
public class DomanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomanagementApplication.class, args);
    }
}
