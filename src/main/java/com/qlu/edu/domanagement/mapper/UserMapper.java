package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 处理管理员用户的持久层接口
 */
@Repository
public interface UserMapper {
    /**
     * 管理员用户登陆校验
     * @param user 登陆账号信息
     * @return  查询的管理员信息
     */
    User login(User user);

    /**
     * 管理员进行修改密码
     * @param uid 管理员id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    Integer changePassword(@Param("uid") Integer uid, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);

    /**
     * 判断是否su管理员
     * @param uid 登陆用户id
     * @return
     */
    Integer isSu(Integer uid);

    /**
     * 查询所有管理员用户名密码
     * @param uid 登陆用户超级管理员需要排除
     * @return
     */
    User[] findUser(Integer uid);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    Integer findUserName(String username);

    /**
     * 添加用户
     * @param username
     * @param password
     * @return
     */
    Integer addUser(String username,String password);

    /**
     * 根据用户id和用户名删除
     * @param uid
     * @param username
     * @return
     */
    Integer deleteUser(Integer uid,String username);

}
