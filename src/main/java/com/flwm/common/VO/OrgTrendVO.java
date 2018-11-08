package com.flwm.common.VO;

import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.FinanceDO;
import lombok.Data;

/**
 * Created by zhoupj on 10/30/18.
 */
@Data
public class OrgTrendVO {

    private String dt;
    private Double fur;
    private Double sbr;

    public OrgTrendVO(FinanceDO financeDO){
        this.dt= DateUtil.getShortFormat(financeDO.getFinDate());
        this.fur=financeDO.getFundHolding()==null?0:financeDO.getFundHolding();
        this.sbr=financeDO.getSbHolding()==null?0:financeDO.getSbHolding();

    }


}
