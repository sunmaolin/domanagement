package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.Guide;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideMapper {

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
