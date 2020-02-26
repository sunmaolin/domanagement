package com.qlu.edu.domanagement.entity;

public class Student {
    private Integer did;
    private String sid;
    private String sname;
    private String ssex;
    private String sphone;
    private String sprofessional;
    private String sclass;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
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
        return "Student{" +
                "did=" + did +
                ", sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sphone='" + sphone + '\'' +
                ", sprofessional='" + sprofessional + '\'' +
                ", sclass='" + sclass + '\'' +
                '}';
    }
}
