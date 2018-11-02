package com.flwm.common.VO;

import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.DayLineDO;
import lombok.Data;

/**
 * Created by zhoupj on 10/31/18.
 */
@Data
public class ShapeVO {

    private String dt;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Double ma10;
    private Double ma50;
    private Double ma120;
    private Double ma250;

    public ShapeVO(DayLineDO dayLineDO){

        this.dt= DateUtil.getShortFormat(dayLineDO.getTradeDate());
        this.open=dayLineDO.getOpen();
        this.close=dayLineDO.getClose();
        this.high=dayLineDO.getHigh();
        this.low=dayLineDO.getLow();
        this.ma10=dayLineDO.getMa10();
        this.ma50=dayLineDO.getMa50();
        this.ma120=dayLineDO.getMa120();
        this.ma250=dayLineDO.getMa250();


    }



}
