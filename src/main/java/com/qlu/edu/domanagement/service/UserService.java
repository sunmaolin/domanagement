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

    /**
     * 修改密码
     * @param uid   管理员id
     * @param oldPassword   旧密码
     * @param newPassword   新密码
     * @return  修改成功，返回行数1修改成功，0失败
     */
    void changePassword(Integer uid,String oldPassword,String newPassword);

    /**
     * 判断是否su用户
     * @param uid
     */
    void isSu(Integer uid);
}
