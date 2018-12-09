package com.flwm.common.VO;

import com.alibaba.fastjson.annotation.JSONField;
import com.flwm.common.util.BeanUtil;
import com.flwm.dal.dao.TradeDO;

import java.util.Date;

public class TradeVO {

    private Integer id;

    private Integer userId;

    private String shareCode;

    private String name;

    @JSONField(format = "yyyy-MM-dd")
    private Date addDate;

    @JSONField(format = "yyyy-MM-dd")
    private Date removeDate;

    private Double buyPrice;

    private Double sellPrice;

    private Integer buyCount;

    private Double yieldRate;

    private Double actualYieldRate;

}
