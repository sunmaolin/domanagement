package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.entity.*;
import com.qlu.edu.domanagement.service.DormitoryService;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dormitory")
public class DormitoryController extends BaseController {

    @Autowired
    DormitoryService dormitoryService;

    @PostMapping("find_all")
    public JsonResult findAll(Integer flag,Integer fid){
        List treeData=dormitoryService.findFloorAndDormitory(flag,fid);
        return new JsonResult(OK,treeData);
    }

    @PostMapping("add_floor")
    public JsonResult addFloor(String fname,Integer start,Integer end){
        if(!"".equalsIgnoreCase(fname)){
            dormitoryService.insertFloor(fname);
        }else{
            dormitoryService.insertFloors(start,end);
        }
        return new JsonResult(OK);
    }

    @PostMapping("add_dormitory")
    public JsonResult addDormitory(Integer fid,String dname,Integer start,Integer end){
        if(!"".equalsIgnoreCase(dname)){
            dormitoryService.insertDormitory(fid,dname);
        }else{
            dormitoryService.insertDormitorys(fid,start,end);
        }
        return new JsonResult(OK);
    }

    @PostMapping("del_floor")
    public JsonResult delFloor(String fname,Integer start,Integer end){
        if(!"".equalsIgnoreCase(fname)){
            dormitoryService.deleteFloor(fname);
        }else{
            dormitoryService.deleteFloors(start, end);
        }
        return new JsonResult(OK);
    }

    @PostMapping("del_dormitory")
    public JsonResult delDormitory(Integer fid,String dname,Integer start,Integer end){
        if(!"".equalsIgnoreCase(dname)){
            dormitoryService.deleteDormitory(fid,dname);
        }else{
            dormitoryService.deleteDormitorys(fid, start, end);
        }
        return new JsonResult(OK);
    }

    @RequestMapping("findDormitoryDisciplinary/{did}")
    public JsonResult<Disciplinary[]>  findDormitoryDisciplinary(@PathVariable("did")Integer did){
        Disciplinary[] disciplinaries=dormitoryService.findDormitoryDisciplinary(did);
        return new JsonResult<>(OK,disciplinaries);
    }

    @RequestMapping("findDormitoryMaintain/{did}")
    public JsonResult<Maintain[]> findDormitoryMaintain(@PathVariable("did")Integer did){
        Maintain[] maintains = dormitoryService.findDormitoryMaintain(did);
        return new JsonResult<>(OK,maintains);
    }

    @CrossOrigin(origins="http://localhost:8081",allowCredentials="true")
    @GetMapping("findAllFloor")
    public JsonResult<Floor[]> findAllFloor(){
        Floor[] floors = dormitoryService.findAllFloor();
        return new JsonResult<>(OK,floors);
    }

    @CrossOrigin(origins="http://localhost:8081",allowCredentials="true")
    @GetMapping("findDormitoryByFid")
    public JsonResult<Dormitory[]> findDormitory(Integer fid){
        if(fid==null){
            return new JsonResult<>(OK);
        }
        Dormitory[] dormitories = dormitoryService.findDormitoyByFid(fid);
        return new JsonResult<>(OK,dormitories);
    }

    @GetMapping("findAllDormitoryDisciplinary")
    public JsonResult<List> findAllDormitoryDisciplinary(){
        List data=dormitoryService.findAllDormitoryDisciplinary();
        return new JsonResult<>(OK,data);
    }

    @PostMapping("submitDisciplinaryRecord")
    public Map submitDisciplinaryRecord(Disciplinary disciplinary, MultipartFile photo, HttpSession session){

        if(!photo.isEmpty()){
            String filename = photoUpload(photo);
            String image="images/upload/"+filename;
            disciplinary.setImage(image);
        }

        dormitoryService.addDormitoryDisciplinary(disciplinary,session);

        Map data=new HashMap();
        data.put("success",true);
        data.put("state",OK);
        return data;
    }

    @GetMapping("findAllMaintain")
    public JsonResult<Map[]> findAllMaintain(){
        Map[] maintains = dormitoryService.findAllMaintain();
        return new JsonResult<>(OK,maintains);
    }

    @PostMapping("addMaintainRecord")
    public Map addMaintainRecord(Maintain maintain){
        dormitoryService.addMaintainRecord(maintain);
        Map data = new HashMap();
        data.put("success",true);
        data.put("state",OK);
        return data;
    }

    @GetMapping("deleteMaintainRecord/{mid}")
    public JsonResult deleteMaintainRecord(@PathVariable("mid")Integer mid){
        dormitoryService.deleteMaintainRecord(mid);
        return new JsonResult(OK);
    }

    @PostMapping("saveMaintainRecord")
    public JsonResult saveMaintainRecord(@RequestBody Maintain[] maintains){
        dormitoryService.updateMaintainRecord(maintains);
        return new JsonResult(OK);
    }

    //解决跨域请求
    @CrossOrigin(origins="http://localhost:8081",allowCredentials="true")
    @GetMapping("findAllNotice")
    public JsonResult<Notice[]> findAllNotice(){
        Notice[] notices = dormitoryService.findAllNotice();
        return new JsonResult<>(OK,notices);
    }

    @PostMapping("addPublishNotice")
    public Map addPublishNotice(Notice notice,HttpSession session){
        dormitoryService.addPublishNotice(notice,session);
        Map data = new HashMap();
        data.put("state",OK);
        data.put("success",true);
        return data;
    }

    @GetMapping("deletePublishNotice/{nid}")
    public JsonResult deletePublishNotice(@PathVariable("nid")Integer nid){
        dormitoryService.deletePublishNotice(nid);
        return new JsonResult(OK);
    }

    @PostMapping("updatePublishNotice")
    public Map updatePublishNotice(Notice notice,HttpSession session){
        dormitoryService.updatePublishNotice(notice,session);
        Map data = new HashMap();
        data.put("state",OK);
        data.put("success",true);
        return data;
    }

    @PostMapping("deleteDormitorysDisciplinary")
    public Map deleteDormitorysDisciplinary(String startTime,String endTime){
        dormitoryService.deleteDormitorysDisciplinary(startTime, endTime);
        Map data = new HashMap();
        data.put("state",OK);
        data.put("success",true);
        return data;
    }


}
