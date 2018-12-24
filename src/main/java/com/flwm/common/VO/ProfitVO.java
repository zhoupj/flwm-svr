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
    private Double jll;
    private Double mll;
    private Double s2;
    private Double s8;



    public ProfitVO(FinanceDO financeDO){

        this.dt= DateUtil.getShortFormat(financeDO.getFinDate());
        this.gsjlr=financeDO.getGsjlr()==null?0:financeDO.getGsjlr();
        this.kfjlr=financeDO.getKfjlr()==null?0:financeDO.getKfjlr();
        this.yyzsr=financeDO.getYyzsr()==null?0:financeDO.getYyzsr();
        this.yyzsrtbzz=financeDO.getYyzsrtbzz()==null?0:financeDO.getYyzsrtbzz();
        this.gsjlrtbzz=financeDO.getGsjlrtbzz()==null?0:financeDO.getGsjlrtbzz();
        this.kfjlrtbzz=financeDO.getKfjlrtbzz()==null?0:financeDO.getKfjlrtbzz();
        this.mll=financeDO.getMll()==null?0:financeDO.getMll();
        this.jll=financeDO.getJll()==null?0:financeDO.getJll();
        this.s2=financeDO.getSsr2()==null?0:financeDO.getSsr2();
        this.s8=financeDO.getSsr8()==null?0:financeDO.getSsr8();

    }



}
