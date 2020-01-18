package com.qlu.edu.domanagement.service.impl;

import com.qlu.edu.domanagement.entity.Dormitory;
import com.qlu.edu.domanagement.entity.Floor;
import com.qlu.edu.domanagement.mapper.DormitoryMapper;
import com.qlu.edu.domanagement.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DormitoryServiceImpl implements DormitoryService {

    @Autowired
    DormitoryMapper dormitoryMapper;

    @Override
    public List findFloorAndDormitory(Integer flag, Integer fid) {
        ArrayList treeData=new ArrayList();
        if(0 == flag){
            Floor[] floors= dormitoryMapper.findFloor();
            for (Floor floor:floors){
                Map allData=new HashMap();
//                Integer id=floor.getFid();
//                if (dormitoryMapper.findDormitoryByFid(id).length==0){
//                    allData.put("leaf",true);
//                }
                allData.put("text",floor.getFname());
                allData.put("fid",floor.getFid());
                treeData.add(allData);
            }
            return treeData;
        }else if (1 == flag){
            Dormitory[] dormitories=dormitoryMapper.findDormitoryByFid(fid);
            for (Dormitory dormitory:dormitories){
                Map allData=new HashMap();
                allData.put("text",dormitory.getDname());
                allData.put("fid",dormitory.getFid());
                allData.put("did",dormitory.getDid());
                allData.put("leaf",true);
                treeData.add(allData);
            }
            return treeData;
        }

        return treeData;
    }
}
