package com.flwm.common.VO;

import com.alibaba.fastjson.annotation.JSONField;
import com.flwm.common.util.BeanUtil;
import com.flwm.dal.dao.BasicDO;
import com.flwm.dal.dao.DayLineDO;
import com.flwm.dal.dao.FinanceDO;
import lombok.Data;

import java.util.Date;

/**
 * Created by zhoupj on 10/29/18.
 */
@Data
public class SearchVO {

    @JSONField(format = "yyyy-MM-dd")
    private Date tradeDate;

    private Double open;//开盘价
    private Double high;//收盘价
    private Double low;
    private Double close;
    private Long volume;
    private Double incof2d;

    private String code;
    private String name;
    private String industry;
    @JSONField(format = "yyyy-MM-dd")
    private Date timetomarket;
    private Double totals;
    private Integer ishighofyear;
    private Double difftohigh250;
    private Integer islowofyear;
   // private Double rps120;
    private Double rps250;
    private Double rps120;
    private Double fluof250d;
    private Double turn;
    private Double pettm;
    private Double hkHoldingAmount;
    private Double fundHolding;
    private Double ssr2;
    private Double isMR;


    public SearchVO() {

    }

    public void fillBasicInfo(BasicDO basicDO){
        if(basicDO!=null){
            this.setTimetomarket(basicDO.getTimetomarket());
            this.setIndustry(basicDO.getIndustry());
            this.setName(basicDO.getName());
        }

    }

    public static SearchVO convert(DayLineDO dayLineDO, FinanceDO financeDO, BasicDO basicDO) {


        SearchVO vo = BeanUtil.convert(dayLineDO, SearchVO.class);
        if (financeDO != null) {
            vo.setFundHolding(financeDO.getFundHolding());
            vo.setSsr2(financeDO.getSsr2());
        }
        if (basicDO != null) {
            vo.setTimetomarket(basicDO.getTimetomarket());
            vo.setIndustry(basicDO.getIndustry());
            vo.setName(basicDO.getName());
        }
        return vo;


    }


}
