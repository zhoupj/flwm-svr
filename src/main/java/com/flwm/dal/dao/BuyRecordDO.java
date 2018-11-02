package com.flwm.dal.dao;

import java.util.Date;

public class BuyRecordDO {
    private Integer id;

    private Integer userId;

    private Date buyDate;

    private Integer actId;

    private Integer isSucess;

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

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Integer getIsSucess() {
        return isSucess;
    }

    public void setIsSucess(Integer isSucess) {
        this.isSucess = isSucess;
    }
}