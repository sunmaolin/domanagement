package com.qlu.edu.domanagement.entity;

/**
 * 宿舍维修记录的实体类
 */
public class Maintain {
    private Integer mid; //维修id
    private Integer did; //宿舍id
    private String content; //维修内容
    private String createUser;  //创建人
    private String createTime;  //创建时间
    private String remark; //备注
    private String maintainTime; //维修时间
    private String maintainUser; //维修人

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(String maintainTime) {
        this.maintainTime = maintainTime;
    }

    public String getMaintainUser() {
        return maintainUser;
    }

    public void setMaintainUser(String maintainUser) {
        this.maintainUser = maintainUser;
    }

    @Override
    public String toString() {
        return "Maintain{" +
                "mid=" + mid +
                ", did=" + did +
                ", content='" + content + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", remark='" + remark + '\'' +
                ", maintainTime='" + maintainTime + '\'' +
                ", maintainUser='" + maintainUser + '\'' +
                '}';
    }
}
