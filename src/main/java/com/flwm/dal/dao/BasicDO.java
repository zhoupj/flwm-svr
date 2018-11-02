package com.flwm.dal.dao;

import java.util.Date;

public class BasicDO {
    private Integer id;

    private String code;

    private String name;

    private String industry;

    private Date timetomarket;

    private Double outstanding;

    private Double totals;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public Date getTimetomarket() {
        return timetomarket;
    }

    public void setTimetomarket(Date timetomarket) {
        this.timetomarket = timetomarket;
    }

    public Double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
    }

    public Double getTotals() {
        return totals;
    }

    public void setTotals(Double totals) {
        this.totals = totals;
    }
}