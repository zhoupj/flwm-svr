package com.flwm.common.VO;

import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.FinanceDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by zhoupj on 10/30/18.
 */
@Data
public class ProfitVO implements Serializable{

    private String dt;
    private Double gsjlr;
    private Double kfjlr;
    private Double yyzsr;
    private Double yyzsrtbzz;
    private Double gsjlrtbzz;
    private Double kfjlrtbzz;
    private Double s2;
    private Double s8;


    public ProfitVO(FinanceDO financeDO){

        this.dt= DateUtil.getShortFormat(financeDO.getFinDate());
        this.gsjlr=financeDO.getGsjlr();
        this.kfjlr=financeDO.getKfjlr();
        this.yyzsr=financeDO.getYyzsr();
        this.yyzsrtbzz=financeDO.getYyzsrtbzz();
        this.gsjlrtbzz=financeDO.getGsjlrtbzz();
        this.kfjlrtbzz=financeDO.getKfjlrtbzz();
        this.s2=financeDO.getSsr2();
        this.s8=financeDO.getSsr8();

    }



}
