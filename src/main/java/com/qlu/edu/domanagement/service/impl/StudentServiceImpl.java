package com.qlu.edu.domanagement.service.impl;

import com.qlu.edu.domanagement.entity.*;
import com.qlu.edu.domanagement.mapper.DormitoryMapper;
import com.qlu.edu.domanagement.mapper.StudentMapper;
import com.qlu.edu.domanagement.service.StudentService;
import com.qlu.edu.domanagement.service.ex.SidExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;

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

        //思路为先每个宿舍取出7人，后根据打乱的周一到周日进行随机

        //拿到所有宿舍id
        Integer[] dids=dormitoryMapper.findAllDormitoryId();
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
            //宿舍没有学生
            if (studentsLength==0){
                continue;
            }
            //宿舍七个人，小于七个人，大于七个人三种情况
            if(studentsLength==7){
                Collections.addAll(studentsDuty,students);
            }else if (studentsLength<7 && studentsLength>3){ //4 5 6 人的情况
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
            }else if(studentsLength>0 && studentsLength<4){ //1 2 3 人的情况
                Collections.addAll(studentsDuty,students);
                List newStudentsDuty=new ArrayList();
                if(studentsLength==1){
                    for (int j=0;j<6;j++){
                        newStudentsDuty.add(students[0]);
                    }
                }else if (studentsLength==2){
                    for (int j=0;j<4;j++){
                        newStudentsDuty.add(students[j%2]);
                    }

                }else if (studentsLength==3){
                    for (int j=0;j<3;j++){
                        newStudentsDuty.add(students[j%3]);
                    }
                }
                //最后一人随机取
                Student student=students[(int)(Math.random()*(studentsLength-1))];
                newStudentsDuty.add(student);
                studentsDuty.addAll(newStudentsDuty);
            }else if (studentsLength>7){ //宿舍人数大于七人的情况
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

    @Override
    public Map[] findAllDisciplinary(boolean sanitation) {
        Map[] map=studentMapper.findStudentAllDisciplinary(sanitation);
        for (Map data:map){
            String sid = (String)data.get("sid");
            //根据学号查找学生
            Student student=studentMapper.findStudentBySid(sid);
            //将学生名放到data中
            data.put("sname",student.getSname());
            Integer did=student.getDid();
            Dormitory dormitory=dormitoryMapper.findDormitoryByDid(did);
            //将宿舍名放到该data中
            data.put("dname",dormitory.getDname());
            Integer fid=dormitory.getFid();
            Floor floor=dormitoryMapper.findFloorByFid(fid);
            //将宿舍楼名放到该data中
            data.put("fname",floor.getFname());
        }
        return map;
    }

    @Override
    public void addStudentDisciplinary(Disciplinary disciplinary, HttpSession session,Integer flag) {
        String createUser = (String)session.getAttribute("username");
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String createTime=sdf.format(now);
        disciplinary.setCreateUser(createUser);
        disciplinary.setCreateTime(createTime);
        if (flag==0){//个人违纪
            disciplinary.setSanitation(false);
        }else if (flag==2){//卫生违纪
            disciplinary.setSanitation(true);
            //获取日历
            Calendar calendar=Calendar.getInstance();
            //今天是第几天
            int day=calendar.get(Calendar.DAY_OF_WEEK)-1;
            String[] weekDay= {"sunday","monday","tuesday","wednesday","thursday","friday","saturday"};
            String today=weekDay[day];
            //根据宿舍id查找本周值日生
            Map randomDuty= studentMapper.findRandomDuty(disciplinary.getDid());
            String sname=(String)randomDuty.get(today);
            //根据姓名,宿舍查找到学号插入
            String sid = studentMapper.findSidBySnameAndDid(sname,disciplinary.getDid());
            disciplinary.setSid(sid);
        }
        disciplinary.setDid(null);
        studentMapper.addStudentDisciplinary(disciplinary);
    }

    @Override
    public void deleteDisciplinaryRecord(Integer pid) {
        studentMapper.deleteDisciplinaryRecord(pid);
    }


}
