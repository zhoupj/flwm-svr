package com.flwm.dal.dao;

import java.util.Date;

public class TradeDO {
    private Integer id;

    private Integer userId;

    private String shareCode;

    private Date addDate;

    private Date removeDate;

    private Double buyPrice;

    private Double sellPrice;

    private Integer buyCount;

    private Double yieldRate;

    private Double actualYieldRate;

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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(Date removeDate) {
        this.removeDate = removeDate;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Double getYieldRate() {
        return yieldRate;
    }

    public void setYieldRate(Double yieldRate) {
        this.yieldRate = yieldRate;
    }

    public Double getActualYieldRate() {
        return actualYieldRate;
    }

    public void setActualYieldRate(Double actualYieldRate) {
        this.actualYieldRate = actualYieldRate;
    }
}