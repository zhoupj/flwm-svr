package com.flwm.dal.dao;

public class ActivityDO {
    private Integer id;

    private String actCode;

    private String actName;

    private String actDesc;

    private Integer actState;

    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActCode() {
        return actCode;
    }

    public void setActCode(String actCode) {
        this.actCode = actCode == null ? null : actCode.trim();
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName == null ? null : actName.trim();
    }

    public String getActDesc() {
        return actDesc;
    }

    public void setActDesc(String actDesc) {
        this.actDesc = actDesc == null ? null : actDesc.trim();
    }

    public Integer getActState() {
        return actState;
    }

    public void setActState(Integer actState) {
        this.actState = actState;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}