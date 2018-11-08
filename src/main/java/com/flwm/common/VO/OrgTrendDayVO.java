package com.flwm.common.VO;

import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.DayLineDO;
import lombok.Data;

/**
 * Created by zhoupj on 10/31/18.
 */
@Data
public class OrgTrendDayVO {

    private String dt;

    private Double hkr;
    private Double hkm;

    public OrgTrendDayVO(DayLineDO dayLineDO){
        this.dt= DateUtil.getShortFormat(dayLineDO.getTradeDate());
        this.hkr=dayLineDO.getHkHoldingRatio()==null?0:dayLineDO.getHkHoldingRatio();
        this.hkm=dayLineDO.getHkHoldingAmount()==null?0:dayLineDO.getHkHoldingAmount();
    }

}
