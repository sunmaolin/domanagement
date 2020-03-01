package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.Disciplinary;
import com.qlu.edu.domanagement.entity.Dormitory;
import com.qlu.edu.domanagement.entity.Floor;
import com.qlu.edu.domanagement.entity.Maintain;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * 宿舍楼与宿舍的持久层接口
 */
@Repository
public interface DormitoryMapper {
    /**
     * 查询所有宿舍楼
     * @return
     */
    Floor[] findFloor();

    /**
     * 根据宿舍楼id查询所有宿舍
     * @Param fid
     * @return
     */
    Dormitory[] findDormitoryByFid(Integer fid);

    /**
     * 查找所有的宿舍id
     * @return
     */
    Integer[] findAllDormitoryId();

    /**
     * 查询宿舍楼名是否存在
     * @param fname
     * @return
     */
    String findFloorName(String fname);

    /**
     * 插入宿舍楼
     * @param fname
     * @return
     */
    Integer insertFloor(String fname);

    /**
     * 查询宿舍名是否存在
     * @param fid
     * @param dname
     * @return
     */
    String findDormitoryName(Integer fid,String dname);

    /**
     * 插入宿舍
     * @param fid
     * @param dname
     * @return
     */
    Integer insertDormitory(Integer fid,String dname);

    /**
     * 查询是否存在子节点
     * @param fname
     * @return
     */
    Integer findChildren(String fname);

    /**
     * 删除宿舍楼
     * @param fname
     */
    void deleteFloor(String fname);

    /**
     * 删除宿舍
     * @param fid
     * @param dname
     */
    void deleteDormitory(Integer fid,String dname);

    /**
     * 查询宿舍违纪信息
     * @param did
     * @return
     */
    Disciplinary[] findDormitoryDisciplinary(Integer did);

    /**
     * 查询宿舍维修记录
     * @param did
     * @return
     */
    Maintain[] findDormitoryMaintain(Integer did);

    /**
     * 查找所有宿舍的违纪信息
     * @return
     */
    Disciplinary[] findAllDormitoryDisciplinary();

    /**
     * 根据did查找宿舍
     * @param did
     * @return
     */
    Dormitory findDormitoryByDid(Integer did);

    /**
     * 根据fid查找宿舍楼
     * @param fid
     * @return
     */
    Floor findFloorByFid(Integer fid);

    /**
     * 查询所有的维修记录
     * @return
     */
    Map[] findAllMaintain();

    /**
     * 增加维修记录
     * @param maintain
     */
    void addMaintainRecord(Maintain maintain);

    /**
     * 根据mid删除维修记录
     * @param mid
     */
    void deleteMaintainRecord(Integer mid);

    /**
     * 更新维修记录表
     * @param maintain
     */
    void updateMaintainRecord(Maintain maintain);
}
