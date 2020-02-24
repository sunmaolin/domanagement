package com.qlu.edu.domanagement.service;

import com.qlu.edu.domanagement.entity.RandomDuty;
import com.qlu.edu.domanagement.entity.Student;

import java.util.List;

/**
 * 处理学生相关功能的业务层接口
 */
public interface StudentService {
    /**
     * 查询宿舍中的所有学生
     * @param did
     * @return
     */
    Student[] findStudentsByDid(Integer did);

    /**
     * 生成随机值日表
     */
    void randomDuty(List weekDay);

    /**
     * 根据宿舍查找值日表
     * @param did
     * @return
     */
    RandomDuty findRandomDutyByDid(Integer did);
}
