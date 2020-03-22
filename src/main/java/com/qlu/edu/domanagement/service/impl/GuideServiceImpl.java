package com.qlu.edu.domanagement.service.impl;

import com.qlu.edu.domanagement.entity.Guide;
import com.qlu.edu.domanagement.mapper.GuideMapper;
import com.qlu.edu.domanagement.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    GuideMapper guideMapper;

    @Override
    public Guide[] findAllGuide() {
        Guide[] guides = guideMapper.findAllGuide();
        return guides;
    }

    @Override
    public void addGuide(Guide guide) {
        guideMapper.addGuide(guide);
    }

    @Override
    public void deleteGuide(Integer gid) {
        guideMapper.deleteGuide(gid);
    }

    @Override
    public Guide[] searchGuide(String searchCondition) {
        return guideMapper.searchGuide(searchCondition);
    }
}
