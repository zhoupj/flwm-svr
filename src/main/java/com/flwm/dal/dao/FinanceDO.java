package com.flwm.dal.dao;

import java.util.Date;

public class FinanceDO {
    private Integer id;

    private String code;

    private Date finDate;

    private Integer finYear;

    private Integer finSeason;

    private Integer finType;

    private Double jll;

    private Double mll;

    private Double jbmgsy;

    private Double mgjyxjl;

    private Double gsjlr;

    private Double kfjlr;

    private Double yyzsr;

    private Double yyzsrtbzz;

    private Double gsjlrtbzz;

    private Double kfjlrtbzz;

    private Double jbmgsytbzz;

    private Double fundHolding;

    private Integer isexpected;

    private Double seasonMa2;

    private Double seasonMa8;

    private Double ssr2;

    private Double ssr8;

    private Double sbHolding;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getFinDate() {
        return finDate;
    }

    public void setFinDate(Date finDate) {
        this.finDate = finDate;
    }

    public Integer getFinYear() {
        return finYear;
    }

    public void setFinYear(Integer finYear) {
        this.finYear = finYear;
    }

    public Integer getFinSeason() {
        return finSeason;
    }

    public void setFinSeason(Integer finSeason) {
        this.finSeason = finSeason;
    }

    public Integer getFinType() {
        return finType;
    }

    public void setFinType(Integer finType) {
        this.finType = finType;
    }

    public Double getJll() {
        return jll;
    }

    public void setJll(Double jll) {
        this.jll = jll;
    }

    public Double getMll() {
        return mll;
    }

    public void setMll(Double mll) {
        this.mll = mll;
    }

    public Double getJbmgsy() {
        return jbmgsy;
    }

    public void setJbmgsy(Double jbmgsy) {
        this.jbmgsy = jbmgsy;
    }

    public Double getMgjyxjl() {
        return mgjyxjl;
    }

    public void setMgjyxjl(Double mgjyxjl) {
        this.mgjyxjl = mgjyxjl;
    }

    public Double getGsjlr() {
        return gsjlr;
    }

    public void setGsjlr(Double gsjlr) {
        this.gsjlr = gsjlr;
    }

    public Double getKfjlr() {
        return kfjlr;
    }

    public void setKfjlr(Double kfjlr) {
        this.kfjlr = kfjlr;
    }

    public Double getYyzsr() {
        return yyzsr;
    }

    public void setYyzsr(Double yyzsr) {
        this.yyzsr = yyzsr;
    }

    public Double getYyzsrtbzz() {
        return yyzsrtbzz;
    }

    public void setYyzsrtbzz(Double yyzsrtbzz) {
        this.yyzsrtbzz = yyzsrtbzz;
    }

    public Double getGsjlrtbzz() {
        return gsjlrtbzz;
    }

    public void setGsjlrtbzz(Double gsjlrtbzz) {
        this.gsjlrtbzz = gsjlrtbzz;
    }

    public Double getKfjlrtbzz() {
        return kfjlrtbzz;
    }

    public void setKfjlrtbzz(Double kfjlrtbzz) {
        this.kfjlrtbzz = kfjlrtbzz;
    }

    public Double getJbmgsytbzz() {
        return jbmgsytbzz;
    }

    public void setJbmgsytbzz(Double jbmgsytbzz) {
        this.jbmgsytbzz = jbmgsytbzz;
    }

    public Double getFundHolding() {
        return fundHolding;
    }

    public void setFundHolding(Double fundHolding) {
        this.fundHolding = fundHolding;
    }

    public Integer getIsexpected() {
        return isexpected;
    }

    public void setIsexpected(Integer isexpected) {
        this.isexpected = isexpected;
    }

    public Double getSeasonMa2() {
        return seasonMa2;
    }

    public void setSeasonMa2(Double seasonMa2) {
        this.seasonMa2 = seasonMa2;
    }

    public Double getSeasonMa8() {
        return seasonMa8;
    }

    public void setSeasonMa8(Double seasonMa8) {
        this.seasonMa8 = seasonMa8;
    }

    public Double getSsr2() {
        return ssr2;
    }

    public void setSsr2(Double ssr2) {
        this.ssr2 = ssr2;
    }

    public Double getSsr8() {
        return ssr8;
    }

    public void setSsr8(Double ssr8) {
        this.ssr8 = ssr8;
    }

    public Double getSbHolding() {
        return sbHolding;
    }

    public void setSbHolding(Double sbHolding) {
        this.sbHolding = sbHolding;
    }
}