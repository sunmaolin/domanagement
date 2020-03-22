package com.qlu.edu.domanagement.entity;

import java.util.Objects;

public class Guide {
    Integer gid;//导员id
    String gname;//导员姓名
    String gphone;//导员电话
    String gsex;//导员性别
    String sprofessional;//专业
    String sclass;//班级

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGphone() {
        return gphone;
    }

    public void setGphone(String gphone) {
        this.gphone = gphone;
    }

    public String getGsex() {
        return gsex;
    }

    public void setGsex(String gsex) {
        this.gsex = gsex;
    }

    public String getSprofessional() {
        return sprofessional;
    }

    public void setSprofessional(String sprofessional) {
        this.sprofessional = sprofessional;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    @Override
    public String toString() {
        return "Guide{" +
                "gid=" + gid +
                ", gname='" + gname + '\'' +
                ", gphone='" + gphone + '\'' +
                ", gsex='" + gsex + '\'' +
                ", sprofessional='" + sprofessional + '\'' +
                ", sclass='" + sclass + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guide guide = (Guide) o;
        return Objects.equals(gid, guide.gid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid);
    }
}
