package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.entity.Disciplinary;
import com.qlu.edu.domanagement.entity.RandomDuty;
import com.qlu.edu.domanagement.entity.Student;
import com.qlu.edu.domanagement.service.StudentService;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @RequestMapping("findPersonalDisciplinary/{sid}")
    public JsonResult<Disciplinary[]> findPersonalDisciplinary(@PathVariable("sid")String sid){
        Disciplinary[] disciplinaries = studentService.findPersonalDisciplinary(sid);
        return new JsonResult<>(OK,disciplinaries);
    }

    @GetMapping("deleteStudent/{sid}")
    public JsonResult deleteStudent(@PathVariable("sid")String sid){
        studentService.deleteStudent(sid);
        return new JsonResult(OK);
    }

    @PostMapping("addStudent/{did}")
    public Map addStudent(@PathVariable("did")Integer did,Student student){
        studentService.addStudent(student,did);
        Map data=new HashMap();
        data.put("success",true);
        data.put("state",OK);
        return data;
    }
}