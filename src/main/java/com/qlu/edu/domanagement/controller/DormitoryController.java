package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.entity.Disciplinary;
import com.qlu.edu.domanagement.entity.Maintain;
import com.qlu.edu.domanagement.service.DormitoryService;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
