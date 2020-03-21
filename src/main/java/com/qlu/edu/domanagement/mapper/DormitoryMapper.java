package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.*;
import org.apache.ibatis.annotations.Param;
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

    /**
     * 查找所有的通知
     * @return
     */
    Notice[] findAllNotice();

    /**
     * 添加已发布内容
     * @param notice
     */
    void addPublishNotice(Notice notice);

    /**
     * 删除已发布内容
     * @param nid
     */
    void deletePublishNotice(Integer nid);

    /**
     * 修改已发布的内容
     * @param notice
     */
    void updatePublishNotice(Notice notice);

    /**
     * 根据宿舍楼名查找fid
     * @param fname
     * @return
     */
    Integer findFidByFname(String fname);

    /**
     * 根据宿舍楼id和宿舍名查找did
     * @param fid
     * @param dname
     * @return
     */
    Integer findDidByFidAndDname(@Param("fid")Integer fid,@Param("dname")String dname);

    /**
     * 批量删除宿舍违纪记录
     */
    void deleteDormitorysDisciplinary(String startTime,String endTime);
}
