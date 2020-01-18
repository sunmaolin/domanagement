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
}
