package com.flwm.dal.dao;

import java.util.Date;

public class UserShareDO {
    private Integer id;

    private Integer userId;

    private String shareCode;

    private Date addTime;

    private Date removeTime;

    private Double incFlu;

    //1 观察 2候选 3 持有4 淘汰
    private Integer sGroup;

    private String tag;

    private String feature;


    public UserShareDO(){

    }

    public UserShareDO(Integer userId,String shareCode,Integer sGroup){
        this.userId=userId;
        this.shareCode=shareCode;
        this.addTime=new Date();
        this.sGroup=sGroup;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode == null ? null : shareCode.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

    public Double getIncFlu() {
        return incFlu;
    }

    public void setIncFlu(Double incFlu) {
        this.incFlu = incFlu;
    }

    public Integer getsGroup() {
        return sGroup;
    }

    public void setsGroup(Integer sGroup) {
        this.sGroup = sGroup;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }
}