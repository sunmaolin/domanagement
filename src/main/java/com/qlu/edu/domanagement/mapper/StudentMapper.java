package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.RandomDuty;
import com.qlu.edu.domanagement.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * 学生的持久层接口
 */
@Repository
public interface StudentMapper {
    /**
     * 根据did查询所有学生
     * @param did
     * @return
     */
    Student[] findStudentsByDid(Integer did);

    /**
     * 将生成的值日表插入到表中
     */
    void insertRandomDuty(RandomDuty randomDuty);

    /**
     * 清空值日表
     */
    void clearRandomDuty();

    /**
     * 根据did查找值日表
     * @return
     */
    RandomDuty findRandomDutyByDid(Integer did);
}
