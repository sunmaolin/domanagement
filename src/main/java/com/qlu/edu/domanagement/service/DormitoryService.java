package com.qlu.edu.domanagement.service;

import java.util.List;

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
}
