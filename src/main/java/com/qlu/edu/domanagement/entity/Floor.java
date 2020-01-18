package com.qlu.edu.domanagement.entity;

import java.util.Objects;

/**
 * 宿舍楼的实体类
 */
public class Floor {
    Integer fid;//宿舍楼id
    String fname;//宿舍楼名

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return Objects.equals(fid, floor.fid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fid);
    }

    @Override
    public String toString() {
        return "Floor{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                '}';
    }
}
