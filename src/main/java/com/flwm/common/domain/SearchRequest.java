package com.flwm.common.domain;

import com.flwm.common.util.DateUtil;
import lombok.Data;

import java.util.List;

/**
 * Created by zhoupj on 10/28/18.
 */
@Data
public class SearchRequest extends PageRequest {

    //data
    private String tradeDate;
    private String code;

    @Deprecated
    private List<String> codes;

    private Boolean lastest;


    //市值
    private Double totals;

    //rps
    private Integer rps250;
    private Integer rps120;
    private Integer rps50;

    //hk
    private Double hkHoldingAmount;
    private Double hkHoldingRatio;

    //fluction
    private Double fluof250d;
    private Double fluof120d;

    private Double fluof80d;
    private Double fluof10d;

    //diff to high
    private Double difftohigh250;

    //is high of year
    private Integer ishighofyear;
    //pe
    private Double pettm;
    //turn
    private Double turn;

    private Integer closeGreaterTo50;
    //funding
    private Double fundHolding;
    //ssr2
    private Double ssr2;

    public String getFinDate(){
        return DateUtil.getReportDate(this.tradeDate);
    }


    public SearchRequest(){

    }

    public SearchRequest(String code,boolean lastest){

        this.code=code;
        if(lastest){
            this.lastest=true;
        }
    }


}