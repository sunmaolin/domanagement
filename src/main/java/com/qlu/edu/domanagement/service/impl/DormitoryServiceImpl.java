package com.qlu.edu.domanagement.service.impl;

import com.qlu.edu.domanagement.entity.Disciplinary;
import com.qlu.edu.domanagement.entity.Dormitory;
import com.qlu.edu.domanagement.entity.Floor;
import com.qlu.edu.domanagement.entity.Maintain;
import com.qlu.edu.domanagement.mapper.DormitoryMapper;
import com.qlu.edu.domanagement.mapper.StudentMapper;
import com.qlu.edu.domanagement.service.DormitoryService;
import com.qlu.edu.domanagement.service.ex.DormitoryNameExistException;
import com.qlu.edu.domanagement.service.ex.FloorNameExistException;
import com.qlu.edu.domanagement.service.ex.HaveChildrenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DormitoryServiceImpl implements DormitoryService {

    @Autowired
    DormitoryMapper dormitoryMapper;

    @Autowired
    StudentMapper studentMapper;

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

    @Override
    public void insertFloor(String fname) {
        
        String name=dormitoryMapper.findFloorName(fname);
        if(name!=null){
            throw new FloorNameExistException(name+"已存在!");
        }
        dormitoryMapper.insertFloor(fname);
    }

    @Override
    public void insertDormitory(Integer fid, String dname) {
        String name=dormitoryMapper.findDormitoryName(fid, dname);
        if(name!=null){
            throw new DormitoryNameExistException(dname+"已存在！");
        }
        dormitoryMapper.insertDormitory(fid, dname);
    }

    @Override
    public void insertFloors(Integer start, Integer end) {
        for (int i=start;i<=end;i++){
            String fname=toChinese(i)+"号楼";
            insertFloor(fname);
        }
    }

    @Override
    public void insertDormitorys(Integer fid, Integer start, Integer end) {
        for (int i=start;i<=end;i++){
            insertDormitory(fid,String.valueOf(i));
        }
    }

    @Override
    public void deleteFloor(String fname) {
        Integer children=dormitoryMapper.findChildren(fname);
        if (children!=0){
            throw new HaveChildrenException("删除中断！该宿舍楼"+fname+"存在子节点！");
        }

        dormitoryMapper.deleteFloor(fname);
    }

    @Override
    public void deleteDormitory(Integer fid, String dname) {
        dormitoryMapper.deleteDormitory(fid, dname);
    }

    @Override
    public void deleteFloors(Integer start, Integer end) {
        for (int i=start;i<=end;i++){
            String fname=toChinese(i)+"号楼";
            deleteFloor(fname);
        }
    }

    @Override
    public void deleteDormitorys(Integer fid, Integer start, Integer end) {
        for (int i=start;i<=end;i++){
            deleteDormitory(fid,String.valueOf(i));
        }
    }

    @Override
    public Disciplinary[] findDormitoryDisciplinary(Integer did) {
        return dormitoryMapper.findDormitoryDisciplinary(did);
    }

    @Override
    public Maintain[] findDormitoryMaintain(Integer did) {
        return dormitoryMapper.findDormitoryMaintain(did);
    }

    @Override
    public Floor[] findAllFloor() {
        return dormitoryMapper.findFloor();
    }

    @Override
    public Dormitory[] findDormitoyByFid(Integer fid) {
        return dormitoryMapper.findDormitoryByFid(fid);
    }

    @Override
    public List findAllDormitoryDisciplinary() {
        List all=new ArrayList();
        try{
            //查找所有的违纪信息
            Disciplinary[] disciplinaries=dormitoryMapper.findAllDormitoryDisciplinary();
            for (Disciplinary disciplinary:disciplinaries){
                //封装数据的map
                Map<String,Object> data=new HashMap();
                Class dis=Class.forName("com.qlu.edu.domanagement.entity.Disciplinary");
                Field[] fields = dis.getDeclaredFields();
                for (Field field : fields){
                    data.put(field.getName(),"");
                }
                for (Map.Entry entry:data.entrySet()){
                    String name=(String)entry.getKey();
                    //创建一属性描述器,将属性描述器映射到Disciplinary类中
                    PropertyDescriptor pd = new PropertyDescriptor(name, Disciplinary.class);
                    //得到getter属性
                    Method getter = pd.getReadMethod();
                    //将方法反射到Disciplinary类中,读出属性的值
                    Object value = getter.invoke(disciplinary,null);
                    data.put(name,value);
                }
                //处理完查出来的对象在处理宿舍楼和宿舍
                Dormitory dormitory = dormitoryMapper.findDormitoryByDid((Integer) data.get("did"));
                data.put("dname",dormitory.getDname());
                Floor floor = dormitoryMapper.findFloorByFid(dormitory.getFid());
                data.put("fname",floor.getFname());
                all.add(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return all;
    }

    @Override
    public void addDormitoryDisciplinary(Disciplinary disciplinary, HttpSession session) {
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String createTime=sdf.format(now);
        String createUser=(String) session.getAttribute("username");
        disciplinary.setCreateTime(createTime);
        disciplinary.setCreateUser(createUser);
        disciplinary.setSid(null);
        studentMapper.addStudentDisciplinary(disciplinary);
    }

    /**
     * 数字转为汉字的处理方法
     * @param number
     * @return
     */
    private String toChinese(Integer number){
        String[] NUMBER_ZH=new String[]{"零","一","二","三","四","五","六","七","八","九","十"};
        if (number<=10){
            return NUMBER_ZH[number];
        }else if (number>10 && number<20){
            return "十"+NUMBER_ZH[Integer.valueOf(String.valueOf(number.toString().charAt(1)))];
        }else if(number.toString().charAt(1)=='0'){
            return NUMBER_ZH[number.toString().charAt(0)-'0']+"十";
        }else{
            return NUMBER_ZH[number.toString().charAt(0)-'0']+"十"+NUMBER_ZH[Integer.valueOf(String.valueOf(number.toString().charAt(1)))];
        }
    }
}
