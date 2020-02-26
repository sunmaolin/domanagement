package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.Disciplinary;
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

    /**
     * 查找学生的个人违纪信息
     * @param sid
     * @return
     */
    Disciplinary[] findPersonalDisciplinary(String sid);

    /**
     * 删除学生表中的学生
     * @param sid
     */
    void deleteStudent(String sid);

    /**
     * 删除违纪表中该学生的违纪
     * @param sid
     */
    void deleteStudentDisciplinary(String sid);

    /**
     * 根据学号查找判断是否存在
     * @param sid
     * @return
     */
    String findSidBySid(String sid);

    /**
     * 添加学生
     * @param student
     */
    void addStudent(Student student);
}