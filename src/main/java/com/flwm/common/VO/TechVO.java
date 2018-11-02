package com.flwm.common.VO;

import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.DayLineDO;
import lombok.Data;

/**
 * Created by zhoupj on 10/30/18.
 */
@Data
public class TechVO {


    private String dt;

    private Double totals;

    private Double rps120;
    private Double rps250;

    private Double turn;
    private Double turn50;

    private Double pettm;

    private Double f250;
    private Double f120;
    private Double f10;

    private Double d250;
    private Double d120;


    public TechVO(DayLineDO dayLineDO){
        this.dt= DateUtil.getShortFormat(dayLineDO.getTradeDate());
        this.totals=dayLineDO.getTotals();
        this.rps120=dayLineDO.getRps120();
        this.rps250=dayLineDO.getRps250();
        this.turn=dayLineDO.getTurn();
        this.turn50=dayLineDO.getTurn50ma();
        this.pettm=dayLineDO.getPettm();
        this.f120=dayLineDO.getFluof120d();
        this.f250=dayLineDO.getFluof250d();
        this.f10=dayLineDO.getFluof10d();
        this.d250=dayLineDO.getDifftohigh250();
        this.d120=dayLineDO.getDifftohigh120();
    }



}
