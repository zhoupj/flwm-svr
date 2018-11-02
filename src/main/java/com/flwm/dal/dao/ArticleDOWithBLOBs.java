package com.flwm.dal.dao;

public class ArticleDOWithBLOBs extends ArticleDO {
    private String ctx;

    private String feature;

    public String getCtx() {
        return ctx;
    }

    public void setCtx(String ctx) {
        this.ctx = ctx == null ? null : ctx.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }
}