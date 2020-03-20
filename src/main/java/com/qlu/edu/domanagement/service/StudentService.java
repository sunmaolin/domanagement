package com.qlu.edu.domanagement.service;

import com.qlu.edu.domanagement.entity.Disciplinary;
import com.qlu.edu.domanagement.entity.RandomDuty;
import com.qlu.edu.domanagement.entity.Student;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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

    /**
     * 查找学生的个人违纪记录
     * @param sid
     * @return
     */
    Disciplinary[] findPersonalDisciplinary(String sid);

    /**
     * 根据sid删除学生
     * @param sid
     */
    void deleteStudent(String sid);

    /**
     * 添加学生
     * @param student
     * @param did
     */
    void addStudent(Student student,Integer did);

    /**
     * 查找学生的违纪情况
     * @param sanitation  true卫生违纪  false个人违纪
     * @return
     */
    Map[] findAllDisciplinary(boolean sanitation);

    /**
     * 插入学生的违纪信息
     * @param disciplinary
     * @param session
     * @param flag 0个人违纪  1卫生违纪
     */
    void addStudentDisciplinary(Disciplinary disciplinary, HttpSession session,Integer flag);

    /**
     * 删除违纪记录
     * @param pid
     */
    void deleteDisciplinaryRecord(Integer pid);

    /**
     * 增加留言信息
     * @param opinion
     */
    void addMessage(String opinion);

    /**
     * 查看所有留言
     * @return
     */
    Map[] findAllMessage();

    /**
     * 批量上传学生
     * @param studentsList
     */
    void addStudents(List<Student> studentsList);

    /**
     * 查找所有的年级
     * @return
     */
    List<String> findAllGrade();

    /**
     * 批量删除毕业生
     * @param grade
     */
    void deleteStudents(Integer grade);
}
