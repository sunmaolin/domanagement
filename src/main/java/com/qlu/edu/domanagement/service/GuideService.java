package com.qlu.edu.domanagement.service;

import com.qlu.edu.domanagement.entity.Guide;

public interface GuideService {

    /**
     * 查找所有导员信息
     * @return
     */
    Guide[] findAllGuide();

    /**
     * 增加导员
     * @param guide
     */
    void addGuide(Guide guide);

    /**
     * 删除导员
     * @param gid
     */
    void deleteGuide(Integer gid);

    /**
     * 根据条件查找导员
     * @param searchCondition
     * @return
     */
    Guide[] searchGuide(String searchCondition);
}
