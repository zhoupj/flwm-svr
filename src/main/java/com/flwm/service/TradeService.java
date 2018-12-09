package com.flwm.service;

import com.flwm.common.VO.MonthCupVO;
import com.flwm.common.VO.SearchVO;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.DateUtil;
import com.flwm.common.util.NumberUtil;
import com.flwm.dal.dao.BasicDO;
import com.flwm.dal.dao.TradeDO;
import com.flwm.dal.mapper.TradeDOMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TradeService {

    @Autowired
    TradeDOMapper tradeDOMapper;
    @Autowired
    private BasicService basicService;

    public void save(Integer userId, TradeDO tradeDO) {


        if (StringUtils.isNumeric(tradeDO.getShareCode()) && basicService.queryByCode(tradeDO.getShareCode()) == null) {
            throw new FMException(FMErrorEnum.CODE_NOT_EXIST);

        } else if (basicService.queryByName(tradeDO.getShareCode()) == null) {
            throw new FMException(FMErrorEnum.CODE_NOT_EXIST);
        }


        tradeDO.setUserId(userId);

        computeYieldRate(tradeDO);

        if (tradeDO.getId() == null) {
            try {
                tradeDOMapper.insert(tradeDO);

            } catch (DuplicateKeyException e) {

            }

        } else {
            tradeDOMapper.updateByPrimaryKeySelective(tradeDO);
        }

    }

    public TradeDO queryById(Integer id) {
        TradeDO td = tradeDOMapper.selectByPrimaryKey(id);
        fillName(td);
        return td;
    }


    private TradeDO fillName(TradeDO td) {
        if (td != null) {
            BasicDO sv = basicService.queryByCode(td.getShareCode());
            if (sv != null) {
                td.setName(sv.getName());
            }
        }
        return td;
    }


    public void deleteById(Integer id) {
        tradeDOMapper.deleteByPrimaryKey(id);
    }


    private void computeYieldRate(TradeDO tradeDO) {

        tradeDO.setYieldRate((tradeDO.getSellPrice() - tradeDO.getBuyPrice()) / tradeDO.getBuyPrice() * 100);

        if (tradeDO.getBuyCount() != null) {
            double buy = tradeDO.getBuyPrice() * tradeDO.getBuyCount();
            double buyCost = buy * 0.0003;
            double sell = tradeDO.getSellPrice() * tradeDO.getBuyCount();
            double sellCost = sell * 0.001 + sell * 0.0003;
            double ar = (sell - buy - buyCost - sellCost) / buy * 100;
            tradeDO.setActualYieldRate(ar);
        }
    }


    public List<TradeDO> queryByUserId(Integer userId, int pn, int sz) {

        if (pn <= 0) {
            pn = 0;
        }
        if (sz <= 0 || sz > 20) {
            sz = 20;
        }
        int offset = pn * sz;

        List<TradeDO> lts = tradeDOMapper.selectByUserId(userId, offset, sz);

        for (TradeDO lt : lts) {
            fillName(lt);
        }
        return lts;
    }


    public List<MonthCupVO> queryStatistics(Integer userId) {

        /**
         * 数据量太大之后，此处要优化，进行分业处理
         */
        List<TradeDO> tradeDOS = queryByUserId(userId, 0, 10000);

        Map<String, List<TradeDO>> map = new HashMap<>();

        for (TradeDO t : tradeDOS) {

            String m = DateUtil.getShortFormat(t.getRemoveDate()).substring(0, 7);

            List<TradeDO> lst = map.get(m);
            if (lst == null) {
                lst = new ArrayList<>();
                map.put(m, lst);
            }
            lst.add(t);

        }


        List<MonthCupVO> vos = new ArrayList<>();

        for (String m : map.keySet()) {

            MonthCupVO tv = new MonthCupVO();

            tv.setMonth(m);

            vos.add(computeEveryMonth(m, map.get(m)));

        }

        vos.sort(Comparator.comparing(MonthCupVO::getMonth));

        return vos;


    }


    private MonthCupVO computeEveryMonth(String m, List<TradeDO> ts) {

        int succCount = 0;
        double yieldRate = 0;
        double lossRate = 0;

        for (TradeDO t : ts) {

            if (t.getSellPrice() >= t.getBuyPrice()) {
                succCount++;
                yieldRate += t.getYieldRate();
            } else {
                lossRate += Math.abs(t.getYieldRate());
            }

        }

        MonthCupVO tv = new MonthCupVO();

        tv.setMonth(m);
        if (succCount == ts.size()) {
            tv.setAvgLossYieldRate(0);
        } else {
            tv.setAvgLossYieldRate(NumberUtil.formatTwo(lossRate / (ts.size() - succCount)));
        }
        tv.setAvgWinYieldRate(NumberUtil.formatTwo(yieldRate / (succCount)));

        tv.setSuccRate(NumberUtil.formatTwo(succCount * 1.0 / ts.size() * 100));


        double fz = tv.getSuccRate() * tv.getAvgWinYieldRate();
        if (fz == 0) {
            tv.setCupV(0);
            return tv;
        }

        double fm = (100 - tv.getSuccRate()) * tv.getAvgLossYieldRate();

        if (fm == 0) {
            tv.setCupV(8);
            return tv;
        }

        tv.setCupV(NumberUtil.formatTwo(fz / fm));

        return tv;


    }


}
