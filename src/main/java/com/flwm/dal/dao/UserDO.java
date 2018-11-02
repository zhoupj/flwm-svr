package com.flwm.dal.dao;

import java.util.Date;

public class UserDO {
    private Integer id;

    private String openId;

    private String name;

    private String alias;

    private Integer isMember;

    private Date memberDeadline;

    private Date lastLoginTime;

    private Integer loginDays;

    private Date thisLoginTime;

    private String feature;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public Date getMemberDeadline() {
        return memberDeadline;
    }

    public void setMemberDeadline(Date memberDeadline) {
        this.memberDeadline = memberDeadline;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginDays() {
        return loginDays;
    }

    public void setLoginDays(Integer loginDays) {
        this.loginDays = loginDays;
    }

    public Date getThisLoginTime() {
        return thisLoginTime;
    }

    public void setThisLoginTime(Date thisLoginTime) {
        this.thisLoginTime = thisLoginTime;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }
}