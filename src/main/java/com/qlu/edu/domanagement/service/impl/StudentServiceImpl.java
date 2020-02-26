package com.qlu.edu.domanagement.service.impl;

import com.qlu.edu.domanagement.entity.Disciplinary;
import com.qlu.edu.domanagement.entity.RandomDuty;
import com.qlu.edu.domanagement.entity.Student;
import com.qlu.edu.domanagement.mapper.DormitoryMapper;
import com.qlu.edu.domanagement.mapper.StudentMapper;
import com.qlu.edu.domanagement.service.StudentService;
import com.qlu.edu.domanagement.service.ex.SidExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    DormitoryMapper dormitoryMapper;

    @Override
    public Student[] findStudentsByDid(Integer did) {
        Student[] students=studentMapper.findStudentsByDid(did);
        return students;
    }

    @Override
    public void randomDuty(List weekDay) {

        //生成之前先清空当周值日表
        studentMapper.clearRandomDuty();

        //拿到所有宿舍id
        Integer[] dids=dormitoryMapper.findAllDormitory();
        //循坏每一个宿舍生成值日表
        for (int i=0;i<dids.length;i++){
            //new一个新的值日表实体类
            RandomDuty randomDuty=new RandomDuty();
            //存储每天的值日信息
            List studentsDuty=new ArrayList();
            //根据宿舍查找出所有学生
            Student[] students=studentMapper.findStudentsByDid(dids[i]);
            //学生的人数
            int studentsLength=students.length;
            if (studentsLength==0){
                continue;
            }
            //宿舍七个人，小于七个人，大于七个人三种情况
            if(studentsLength==7){
                Collections.addAll(studentsDuty,students);
            }else if (studentsLength<7){
                Collections.addAll(studentsDuty,students);
                List newStudentsDuty=new ArrayList();
                //还需要几个人
                int need=1;
                while (true){
                    //随机抽取一个学生
                    Student student=students[(int)(Math.random()*(studentsLength-1))];
                    if(newStudentsDuty.contains(student)){
                        continue;
                    }
                    newStudentsDuty.add(student);
                    if(++need>7-studentsLength){
                        break;
                    }
                }
                studentsDuty.addAll(newStudentsDuty);
            }else if (studentsLength>7){
                int need=1;
                List newStudentsDuty=new ArrayList();
                while(true){
                    Student student=students[(int)(Math.random()*(studentsLength-1))];
                    if (newStudentsDuty.contains(student)){
                        continue;
                    }
                    newStudentsDuty.add(student);
                    if(++need>7){
                        break;
                    }
                }
                studentsDuty.addAll(newStudentsDuty);
            }
            //遍历乱序的日期
            for (int j=0;j<weekDay.size();j++){
                Student student=(Student) studentsDuty.get(j);
                randomDuty.setDid(student.getDid());
                switch ((String) weekDay.get(j)){
                    case "周一":
                        randomDuty.setMonday(student.getSname());
                        break;
                    case "周二":
                        randomDuty.setTuesday(student.getSname());
                        break;
                    case "周三":
                        randomDuty.setWednesday(student.getSname());
                        break;
                    case "周四":
                        randomDuty.setThursday(student.getSname());
                        break;
                    case "周五":
                        randomDuty.setFriday(student.getSname());
                        break;
                    case "周六":
                        randomDuty.setSaturday(student.getSname());
                        break;
                    case "周日":
                        randomDuty.setSunday(student.getSname());
                        break;
                }
            }
            studentMapper.insertRandomDuty(randomDuty);
        }
    }

    @Override
    public RandomDuty findRandomDutyByDid(Integer did) {
        return studentMapper.findRandomDutyByDid(did);
    }

    @Override
    public Disciplinary[] findPersonalDisciplinary(String sid) {
        return studentMapper.findPersonalDisciplinary(sid);
    }

    @Override
    public void deleteStudent(String sid) {
        //删除student表中的数据
        studentMapper.deleteStudent(sid);
        //删除perordordisciplinary表中的数据
        studentMapper.deleteStudentDisciplinary(sid);
    }

    @Override
    public void addStudent(Student student, Integer did) {
        String sid=studentMapper.findSidBySid(student.getSid());
        if(sid!=null){
            throw new SidExistException("该学号已存在！请检查");
        }
        student.setDid(did);
        studentMapper.addStudent(student);
    }
}
