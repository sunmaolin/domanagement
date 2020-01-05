package com.qlu.edu.domanagement.mapper;

import com.qlu.edu.domanagement.entity.User;
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
     * 根据管理员用户名查询是否存在
     * @param username  管理员用户名
     * @return  是否存在
     */
    Integer findByUsername(String username);
}
