package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.entity.Disciplinary;
import com.qlu.edu.domanagement.entity.RandomDuty;
import com.qlu.edu.domanagement.entity.Student;
import com.qlu.edu.domanagement.service.DormitoryService;
import com.qlu.edu.domanagement.service.StudentService;
import com.qlu.edu.domanagement.util.ImportExcel;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("student")
public class StudentController extends BaseController {
    @Autowired
    StudentService studentService;

    @Autowired
    DormitoryService dormitoryService;

    //解决跨域请求
    @CrossOrigin(origins="http://localhost:8081",allowCredentials="true")
    @RequestMapping("find/{did}")
    public JsonResult<Student> findStudentsByDid(@PathVariable("did")Integer did){
        if (did==null){
            return new JsonResult<>(OK);
        }
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

    //解决跨域请求
    @CrossOrigin(origins="http://localhost:8081",allowCredentials="true")
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

    @GetMapping("/findAllStudentDisciplinary/{sanitation}")
    public JsonResult<Map[]> findAllStudentDisciplinary(@PathVariable("sanitation") boolean sanitation){
        Map[] data = studentService.findAllDisciplinary(sanitation);
        return new JsonResult<>(OK,data);
    }

    @PostMapping("/submitDisciplinaryRecord")
    public Map submitDisciplinaryRecord(Integer flag, Disciplinary disciplinary, MultipartFile photo, HttpSession session){

        if(!photo.isEmpty()){
            String filename = photoUpload(photo);
            String image="images/upload/"+filename;
            disciplinary.setImage(image);
        }

        studentService.addStudentDisciplinary(disciplinary,session,flag);

        Map data=new HashMap();
        data.put("success",true);
        data.put("state",OK);
        return data;
    }

    @GetMapping("deleteDisciplinaryRecord/{pid}")
    public JsonResult deleteDisciplinaryRecord(@PathVariable("pid")Integer pid){
        studentService.deleteDisciplinaryRecord(pid);
        return new JsonResult(OK);
    }

    //解决跨域请求
    @CrossOrigin(origins="http://localhost:8081",allowCredentials="true")
    @PostMapping("/addMessage")
    public JsonResult addMessage(@RequestBody Map opinion){
        studentService.addMessage((String)opinion.get("opinion"));
        return new JsonResult(OK);
    }

    @GetMapping("findAllMessage")
    public JsonResult<Map[]> findAllMessage(){
        Map[] data = studentService.findAllMessage();
        return new JsonResult<>(OK,data);
    }

    @PostMapping("addStudents")
    public Map addStudents(MultipartFile studentsFile){
        ImportExcel importExcel = new ImportExcel(studentsFile);
        if(!importExcel.typeError()){
            throw new RuntimeException("文件类型错误，支持xlsx，xls");
        }
        try {
            List<Student> studentsList = importExcel.importStudent(dormitoryService);
            studentService.addStudents(studentsList);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("io异常");
        }

        Map data = new HashMap();
        data.put("success",true);
        data.put("state",2000);
        return data;
    }

    @GetMapping("getAllGrade")
    public JsonResult getAllGrade(){
        List classes = studentService.findAllGrade();
        return new JsonResult(OK,classes);
    }

    @RequestMapping("deleteStudents")
    public Map deleteStudents(Integer grade){
        studentService.deleteStudents(grade);
        Map data = new HashMap();
        data.put("state",OK);
        data.put("success",true);
        return data;
    }



}
