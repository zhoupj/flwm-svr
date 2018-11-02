package com.flwm.dal.dao;

import java.util.Date;

public class DayLineDO {
    private Integer id;

    private Date tradeDate;

    private String code;

    private Double open;

    private Double high;

    private Double low;

    private Double close;

    private Long volume;

    private Double turn;

    private Integer tradestatus;

    private Integer isst;

    private Double totals;

    private Integer ishighofyear;

    private Integer ishighofhistory;

    private Integer islowofyear;

    private Integer islowofhistory;

    private Double incof250;

    private Double incof120;

    private Double incof50;

    private Double rps120;

    private Double rps250;

    private Double rps50;

    private Double fluof250d;
    private Double fluof120d;
    private Double fluof80d;
    private Double fluof10d;
    private Double fluof5d;

    private Double difftohigh250;

    private Double difftohigh120;

    private Double difftohigh80;

    private Double sps120;

    private Double sps250;

    private Double sps80;

    private Double ma5;

    private Double ma10;

    private Double ma20;

    private Double ma50;

    private Double ma120;

    private Double ma250;

    private Double turn10ma;

    private Double turn50ma;

    private Double turninctoyesterday;

    private Double turnincto50day;

    private Double pettm;

    private Double curnumpettm1y;

    private Double curnumpettm3y;

    private Double curnumpettm5y;

    private Double curnumpettmall;

    private Double hkHoldingAmount;

    private Double hkHoldingRatio;

    private Double incof2d;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getTurn() {
        return turn;
    }

    public void setTurn(Double turn) {
        this.turn = turn;
    }

    public Integer getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(Integer tradestatus) {
        this.tradestatus = tradestatus;
    }

    public Integer getIsst() {
        return isst;
    }

    public void setIsst(Integer isst) {
        this.isst = isst;
    }

    public Double getTotals() {
        return totals;
    }

    public void setTotals(Double totals) {
        this.totals = totals;
    }

    public Integer getIshighofyear() {
        return ishighofyear;
    }

    public void setIshighofyear(Integer ishighofyear) {
        this.ishighofyear = ishighofyear;
    }

    public Integer getIshighofhistory() {
        return ishighofhistory;
    }

    public void setIshighofhistory(Integer ishighofhistory) {
        this.ishighofhistory = ishighofhistory;
    }

    public Integer getIslowofyear() {
        return islowofyear;
    }

    public void setIslowofyear(Integer islowofyear) {
        this.islowofyear = islowofyear;
    }

    public Integer getIslowofhistory() {
        return islowofhistory;
    }

    public void setIslowofhistory(Integer islowofhistory) {
        this.islowofhistory = islowofhistory;
    }

    public Double getIncof250() {
        return incof250;
    }

    public void setIncof250(Double incof250) {
        this.incof250 = incof250;
    }

    public Double getIncof120() {
        return incof120;
    }

    public void setIncof120(Double incof120) {
        this.incof120 = incof120;
    }

    public Double getIncof50() {
        return incof50;
    }

    public void setIncof50(Double incof50) {
        this.incof50 = incof50;
    }

    public Double getRps120() {
        return rps120;
    }

    public void setRps120(Double rps120) {
        this.rps120 = rps120;
    }

    public Double getRps250() {
        return rps250;
    }

    public void setRps250(Double rps250) {
        this.rps250 = rps250;
    }

    public Double getRps50() {
        return rps50;
    }

    public void setRps50(Double rps50) {
        this.rps50 = rps50;
    }

    public Double getFluof250d() {
        return fluof250d;
    }

    public void setFluof250d(Double fluof250d) {
        this.fluof250d = fluof250d;
    }

    public Double getFluof120d() {
        return fluof120d;
    }

    public void setFluof120d(Double fluof120d) {
        this.fluof120d = fluof120d;
    }

    public Double getFluof80d() {
        return fluof80d;
    }

    public void setFluof80d(Double fluof80d) {
        this.fluof80d = fluof80d;
    }

    public Double getFluof10d() {
        return fluof10d;
    }

    public void setFluof10d(Double fluof10d) {
        this.fluof10d = fluof10d;
    }

    public Double getFluof5d() {
        return fluof5d;
    }

    public void setFluof5d(Double fluof5d) {
        this.fluof5d = fluof5d;
    }

    public Double getDifftohigh250() {
        return difftohigh250;
    }

    public void setDifftohigh250(Double difftohigh250) {
        this.difftohigh250 = difftohigh250;
    }

    public Double getDifftohigh120() {
        return difftohigh120;
    }

    public void setDifftohigh120(Double difftohigh120) {
        this.difftohigh120 = difftohigh120;
    }

    public Double getDifftohigh80() {
        return difftohigh80;
    }

    public void setDifftohigh80(Double difftohigh80) {
        this.difftohigh80 = difftohigh80;
    }

    public Double getSps120() {
        return sps120;
    }

    public void setSps120(Double sps120) {
        this.sps120 = sps120;
    }

    public Double getSps250() {
        return sps250;
    }

    public void setSps250(Double sps250) {
        this.sps250 = sps250;
    }

    public Double getSps80() {
        return sps80;
    }

    public void setSps80(Double sps80) {
        this.sps80 = sps80;
    }

    public Double getMa5() {
        return ma5;
    }

    public void setMa5(Double ma5) {
        this.ma5 = ma5;
    }

    public Double getMa10() {
        return ma10;
    }

    public void setMa10(Double ma10) {
        this.ma10 = ma10;
    }

    public Double getMa20() {
        return ma20;
    }

    public void setMa20(Double ma20) {
        this.ma20 = ma20;
    }

    public Double getMa50() {
        return ma50;
    }

    public void setMa50(Double ma50) {
        this.ma50 = ma50;
    }

    public Double getMa120() {
        return ma120;
    }

    public void setMa120(Double ma120) {
        this.ma120 = ma120;
    }

    public Double getMa250() {
        return ma250;
    }

    public void setMa250(Double ma250) {
        this.ma250 = ma250;
    }

    public Double getTurn10ma() {
        return turn10ma;
    }

    public void setTurn10ma(Double turn10ma) {
        this.turn10ma = turn10ma;
    }

    public Double getTurn50ma() {
        return turn50ma;
    }

    public void setTurn50ma(Double turn50ma) {
        this.turn50ma = turn50ma;
    }

    public Double getTurninctoyesterday() {
        return turninctoyesterday;
    }

    public void setTurninctoyesterday(Double turninctoyesterday) {
        this.turninctoyesterday = turninctoyesterday;
    }

    public Double getTurnincto50day() {
        return turnincto50day;
    }

    public void setTurnincto50day(Double turnincto50day) {
        this.turnincto50day = turnincto50day;
    }

    public Double getPettm() {
        return pettm;
    }

    public void setPettm(Double pettm) {
        this.pettm = pettm;
    }

    public Double getCurnumpettm1y() {
        return curnumpettm1y;
    }

    public void setCurnumpettm1y(Double curnumpettm1y) {
        this.curnumpettm1y = curnumpettm1y;
    }

    public Double getCurnumpettm3y() {
        return curnumpettm3y;
    }

    public void setCurnumpettm3y(Double curnumpettm3y) {
        this.curnumpettm3y = curnumpettm3y;
    }

    public Double getCurnumpettm5y() {
        return curnumpettm5y;
    }

    public void setCurnumpettm5y(Double curnumpettm5y) {
        this.curnumpettm5y = curnumpettm5y;
    }

    public Double getCurnumpettmall() {
        return curnumpettmall;
    }

    public void setCurnumpettmall(Double curnumpettmall) {
        this.curnumpettmall = curnumpettmall;
    }

    public Double getHkHoldingAmount() {
        return hkHoldingAmount;
    }

    public void setHkHoldingAmount(Double hkHoldingAmount) {
        this.hkHoldingAmount = hkHoldingAmount;
    }

    public Double getHkHoldingRatio() {
        return hkHoldingRatio;
    }

    public void setHkHoldingRatio(Double hkHoldingRatio) {
        this.hkHoldingRatio = hkHoldingRatio;
    }

    public Double getIncof2d() {
        return incof2d;
    }

    public void setIncof2d(Double incof2d) {
        this.incof2d = incof2d;
    }
}