package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.entity.Guide;
import com.qlu.edu.domanagement.service.GuideService;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("guide")
public class GuideController extends BaseController{

    @Autowired
    GuideService guideService;

    @GetMapping("findAllGuide")
    public JsonResult findAllGuide(){
        Guide[] guides = guideService.findAllGuide();
        return new JsonResult(OK,guides);
    }

    @PostMapping("addGuide")
    public Map addGuide(Guide guide){
        Map data = new HashMap<>();
        guideService.addGuide(guide);
        data.put("state",OK);
        data.put("success",true);
        return data;
    }

    @PostMapping("deleteGuide")
    public JsonResult deleteGuide(Integer gid){
        guideService.deleteGuide(gid);
        return new JsonResult(OK);
    }

    @PostMapping("findGuide")
    public JsonResult searchGuide(String searchCondition){
        Guide[] guides = guideService.searchGuide(searchCondition);
        return new JsonResult(OK,guides);
    }

}
