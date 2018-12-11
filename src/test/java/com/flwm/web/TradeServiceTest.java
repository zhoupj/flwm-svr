package com.flwm.web;


import com.alibaba.fastjson.JSON;
import com.flwm.common.VO.MonthCupVO;
import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.TradeDO;
import com.flwm.dal.mapper.TradeDOMapper;
import com.flwm.service.TradeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServiceTest {

    @Autowired
    private TradeDOMapper tradeDOMapper;

    @Autowired
    private TradeService tradeService;


    @Test
    public void testTrade(){

        TradeDO tradeDO=new TradeDO();
        tradeDO.setUserId(1);
        tradeDO.setShareCode("000234");
        tradeDO.setAddDate(DateUtil.parseShortFormat("2018-11-12"));
        tradeDO.setRemoveDate(new Date());
        tradeDO.setBuyCount(1000);
        tradeDO.setBuyPrice(10.00);
        tradeDO.setSellPrice(20.10);

        tradeService.save(1,tradeDO);


        tradeDO=new TradeDO();
        tradeDO.setUserId(1);
        tradeDO.setShareCode("000434");
        tradeDO.setAddDate(DateUtil.parseShortFormat("2018-10-12"));
        tradeDO.setRemoveDate(new Date());
        tradeDO.setBuyCount(1000);
        tradeDO.setBuyPrice(4.00);
        tradeDO.setSellPrice(6.10);

        tradeService.save(1,tradeDO);


        tradeDO=new TradeDO();
        tradeDO.setUserId(1);
        tradeDO.setShareCode("003454");
        tradeDO.setAddDate(DateUtil.parseShortFormat("2018-10-11"));
        tradeDO.setRemoveDate(new Date());
        tradeDO.setBuyCount(1000);
        tradeDO.setBuyPrice(10.00);
        tradeDO.setSellPrice(5.10);

        tradeService.save(1,tradeDO);


        List<TradeDO> tradeDOList= tradeService.queryByUserId(1,0,10);

        System.out.println(JSON.toJSONString(tradeDOList));

        Assert.assertTrue(tradeDOList.size()>0);

        List<MonthCupVO> vos= tradeService.queryStatistics(1);

        System.out.println(JSON.toJSONString(vos));

        Assert.assertTrue(vos.size()>0);
    }


    @Test
    public void testGet(){
        tradeService.queryByUserId(1,0,200);
    }
}
