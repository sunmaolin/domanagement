package com.qlu.edu.domanagement.entity;

import java.util.Objects;

/**
 * 宿舍的实体类
 */
public class Dormitory {
    Integer did;//宿舍id
    Integer fid;//宿舍楼id
    String dname;//宿舍名

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dormitory dormitory = (Dormitory) o;
        return Objects.equals(did, dormitory.did) &&
                Objects.equals(fid, dormitory.fid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(did, fid);
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "did=" + did +
                ", fid=" + fid +
                ", dname='" + dname + '\'' +
                '}';
    }
}
