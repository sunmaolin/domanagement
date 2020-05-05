package com.qlu.edu.domanagement.service;

import com.qlu.edu.domanagement.entity.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 处理宿舍楼与宿舍的相关业务层接口
 */
public interface DormitoryService {

    /**
     * 查询所有宿舍楼与宿舍进行组合返回
     * @param flag 返回数据的标志位，0返回宿舍楼，1返回宿舍号
     * @param fid 宿舍楼
     * @return
     */
    List findFloorAndDormitory(Integer flag,Integer fid);

    /**
     * 插入宿舍楼
     * @param fname 插入的宿舍楼名称
     */
    void insertFloor(String fname);

    /**
     * 插入宿舍
     * @param fid
     * @param dname
     */
    void insertDormitory(Integer fid,String dname);

    /**
     * 批量插入宿舍楼
     * @param start
     * @param end
     */
    void insertFloors(Integer start,Integer end);

    /**
     * 批量插入宿舍
     * @param fid
     * @param start
     * @param end
     */
    void insertDormitorys(Integer fid,Integer start,Integer end);

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
     * 批量删除宿舍楼
     * @param start
     * @param end
     */
    void deleteFloors(Integer start,Integer end);

    /**
     * 批量删除宿舍
     * @param fid
     * @param start
     * @param end
     */
    void deleteDormitorys(Integer fid,Integer start,Integer end);

    /**
     * 查找宿舍违纪内容
     * @return
     */
    Disciplinary[] findDormitoryDisciplinary(Integer did);

    /**
     * 查找宿舍维修记录
     * @param did
     * @return
     */
    Maintain[] findDormitoryMaintain(Integer did);

    /**
     * 查找所有的宿舍楼
     * @return
     */
    Floor[] findAllFloor();

    /**
     * 根据宿舍楼id查找所有宿舍
     * @param fid
     * @return
     */
    Dormitory[] findDormitoyByFid(Integer fid);

    /**
     * 查找所有宿舍的违纪信息
     * @return
     */
    List findAllDormitoryDisciplinary();

    /**
     * 增加宿舍的违纪信息
     * @param disciplinary
     * @param session
     */
    void addDormitoryDisciplinary(Disciplinary disciplinary, HttpSession session);

    /**
     * 查询所有的维修信息
     * @return
     */
    Map[] findAllMaintain();

    /**
     * 增加维修记录
     * @param maintain
     */
    void addMaintainRecord(Maintain maintain);

    /**
     * 删除维修记录
     * @param mid
     */
    void deleteMaintainRecord(Integer mid);

    /**
     * 更新维修记录表
     * @param maintains
     */
    void updateMaintainRecord(Maintain[] maintains);

    /**
     * 查找所有的通知信息
     * @return
     */
    Notice[] findAllNotice();

    /**
     * 添加已发布通知
     * @param notice
     */
    void addPublishNotice(Notice notice,HttpSession session);

    /**
     * 删除已发布内容
     * @param nid
     */
    void deletePublishNotice(Integer nid);

    /**
     * 修改已发布的内容
     * @param notice
     * @param session
     */
    void updatePublishNotice(Notice notice,HttpSession session);

    /**
     * 根据宿舍楼名查找id
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
    Integer findDidByFidAndDname(Integer fid,String dname);

    /**
     * 批量删除宿舍违纪记录
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    void deleteDormitorysDisciplinary(String startTime,String endTime);

    /**
     * 发布当日卫生检查情况
     */
    void publishDormitoryCheck();
}
