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

    /**
     * 查询所有管理员和用户名密码
     * @param uid 排除当前用户
     * @return
     */
    User[] findUser(Integer uid);

    /**
     * 根据用户id和用户名删除用户
     * @param uid
     * @param username
     */
    void deleteUser(Integer uid,String username);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    void findUsername(String username);

    /**
     * 新增管理员
     * @param username
     * @param password
     * @return
     */
    void addUser(String username,String password);
}
