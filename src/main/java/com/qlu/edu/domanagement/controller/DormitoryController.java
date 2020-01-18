package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.service.DormitoryService;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
