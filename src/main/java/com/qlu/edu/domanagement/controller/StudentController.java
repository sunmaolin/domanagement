package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.entity.RandomDuty;
import com.qlu.edu.domanagement.entity.Student;
import com.qlu.edu.domanagement.service.StudentService;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController extends BaseController {
    @Autowired
    StudentService studentService;

    @RequestMapping("find/{did}")
    public JsonResult<Student> findStudentsByDid(@PathVariable("did")Integer did){
        Student[] students=studentService.findStudentsByDid(did);
        return new JsonResult(OK,students);
    }

    @RequestMapping("randomDuty")
    public JsonResult<Void> randomDuty(){
        List weekDay=new ArrayList<String>();
        Collections.addAll(weekDay,"周一","周二","周三","周四","周五","周六","周日");
        //打乱顺序
        Collections.shuffle(weekDay);
        studentService.randomDuty(weekDay);
        return new JsonResult<>(OK);
    }

    @RequestMapping("findRandomDuty/{did}")
    public JsonResult<RandomDuty> findRandomDuty(@PathVariable("did")Integer did){
        return new JsonResult<>(OK,studentService.findRandomDutyByDid(did));
    }
}
