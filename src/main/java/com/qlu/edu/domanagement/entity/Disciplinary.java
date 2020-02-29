package com.qlu.edu.domanagement.entity;

/**
 * 宿舍和学生的违纪信息实体类
 */
public class Disciplinary {
    private Integer pid;//违纪信息id
    private Integer did;//宿舍id
    private String sid;//学生id
    private String content;//违纪内容
    private String createUser;
    private String createTime;
    private String modifyUser;
    private String modifyTime;
    private String image;
    private Boolean sanitation;//true为卫生违纪 false为个人违纪

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getSanitation() {
        return sanitation;
    }

    public void setSanitation(Boolean sanitation) {
        this.sanitation = sanitation;
    }

    @Override
    public String toString() {
        return "Disciplinary{" +
                "pid=" + pid +
                ", did=" + did +
                ", sid='" + sid + '\'' +
                ", content='" + content + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", modifyUser='" + modifyUser + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", image='" + image + '\'' +
                ", sanitation=" + sanitation +
                '}';
    }
}
