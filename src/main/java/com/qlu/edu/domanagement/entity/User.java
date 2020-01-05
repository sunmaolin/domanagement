package com.qlu.edu.domanagement.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 管理员用户的实体类
 */
public class User implements Serializable {
    private Integer uid;//管理员id
    private String username;//管理员用户名
    private String password;//管理员密码
    private Integer su;//是否为超级管理员

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSu() {
        return su;
    }

    public void setSu(Integer su) {
        this.su = su;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", su=" + su +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uid, user.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }
}
