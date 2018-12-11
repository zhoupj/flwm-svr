package com.flwm.controller;

import com.flwm.common.VO.MonthCupVO;
import com.flwm.common.cache.UserCache;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.dal.dao.TradeDO;
import com.flwm.service.TradeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cup", method = RequestMethod.POST)
public class TradeRecordController {


    @Autowired
    private TradeService tradeService;


    @PostMapping("/list")
    public List<TradeDO> query(@RequestParam(value = "pn") Integer pn, @RequestParam(value = "sz") Integer sz) {

        if (pn < 0) {
            pn = 0;
        }

        if (sz <= 0 || sz > 20) {
            sz = 20;
        }

        return tradeService.queryByUserId(UserCache.getUserId(), pn, sz);
    }

    @PostMapping("/query")
    public TradeDO queryById(@RequestParam(value = "id") Integer id) {
        return tradeService.queryById(id);
    }

    @PostMapping("/del")
    public boolean deleteById(@RequestParam(value = "id") Integer id) {
        tradeService.deleteById(id);
        return true;
    }

    @PatchMapping("/save")
    public boolean save(@RequestBody TradeDO tradeDO) {

        if (tradeDO == null || tradeDO.getBuyPrice() == null || tradeDO.getSellPrice() == null || StringUtils.isBlank(tradeDO.getShareCode())) {
            throw new FMException(FMErrorEnum.PARAM_EXCEPTION);
        }
        tradeService.save(UserCache.getUserId(), tradeDO);
        return true;
    }

    @PatchMapping("/cup")
    public List<MonthCupVO> queryCup() {
        return tradeService.queryStatistics(UserCache.getUserId());
    }
}
