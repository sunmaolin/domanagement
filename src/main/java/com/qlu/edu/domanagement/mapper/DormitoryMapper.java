package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.Dormitory;
import com.qlu.edu.domanagement.entity.Floor;
import org.springframework.stereotype.Repository;


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
    Integer[] findAllDormitory();

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

}
