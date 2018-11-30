package com.flwm.dal.dao;

import lombok.Data;

import java.util.Date;

@Data
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

    private Integer isMR;

}