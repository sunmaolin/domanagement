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


}
