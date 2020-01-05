package com.qlu.edu.domanagement.service;

import com.qlu.edu.domanagement.entity.User;

/**
 * 处理管理员用户相关功能的业务层接口
 */

public interface UserService {
    /**
     * 用户登陆
     * @param user  登陆用户的信息
     * @return  成功登陆的用户信息
     */
    User login(User user);
}
