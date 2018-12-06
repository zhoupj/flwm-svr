package com.flwm.service;

import com.flwm.common.VO.*;
import com.flwm.common.cache.CacheConfig;
import com.flwm.common.constant.IndexTypeConstant;
import com.flwm.common.domain.FinRequest;
import com.flwm.common.domain.SearchRequest;
import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.BasicDO;
import com.flwm.dal.dao.DayLineDO;
import com.flwm.dal.dao.FinanceDO;
import com.flwm.dal.mapper.BasicDOMapper;
import com.flwm.dal.mapper.DayLineDOMapper;
import com.flwm.dal.mapper.FinanceDOMapper;
import com.flwm.dal.mapper.SearchMapper;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by zhoupj on 10/28/18.
 */
@Service
@Slf4j
public class SearchService implements InitializingBean {


    private static final int SEASON = 1;
    private static final int YEAR = 0;
    private static Map<String, SearchVO> latestVO = new HashMap<>();
    private static String latestDT = null;

    @Autowired
    private BasicDOMapper basicDOMapper;
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private DayLineDOMapper dayLineDOMapper;
    @Autowired
    private FinanceDOMapper financeDOMapper;


    public List<SearchVO> searchOld(SearchRequest request) {

        List<SearchVO> vos = searchMapper.selectByCond(request);
        return mergeWithBasicInfo(vos);


    }

    private List<SearchVO> mergeWithBasicInfo(List<SearchVO> vos) {

        for (SearchVO v : vos) {
            BasicDO basic = basicDOMapper.selectByCode(v.getCode());
            v.fillBasicInfo(basic);
        }
        return vos;
    }


    /**
     * Note:
     * Spring 缓存注解是基于Spring AOP切面，必须走代理才能生效，
     * 同类调用或者子类调用父类带有缓存注解的方法时属于内部调用，没有走代理，所以注解不生效。
     */
    @Cacheable(value = CacheConfig.shareDate, key = "#dt", unless = "#result==null || #result.size()==0")
    public List<SearchVO> searchByDate(String dt) {

        synchronized (dt.intern()) {

            log.info("searchByDate -> load data " + dt);

//            List<String> codes= basicDOMapper.selectCodes();
//            List<SearchVO> searchVOS = new ArrayList<>();
//
//            codes.stream().forEach(code->{
//                SearchVO searchVO=getOneByCodeAndDt(code,dt);
//                if(searchVO!=null){
//                    searchVOS.add(searchVO);
//                }
//            });
//
//
//            if (StringUtils.equals(latestDT, dt)) {
//                cacheNewsVO();
//            }
//
//            return searchVOS;


            List<SearchVO> searchVOS = new ArrayList<>();

            SearchRequest dayLineSearch = new SearchRequest();
            dayLineSearch.setTradeDate(dt);
            dayLineSearch.setPageNo(1);
            dayLineSearch.setPageSize(Integer.MAX_VALUE);
            List<DayLineDO> dayLineDOS = dayLineDOMapper.selectByCond(dayLineSearch);

            if (dayLineDOS.size() == 0) {
                return searchVOS;
            }

            Map<String, FinanceDO> fsMap = getFinanceMap(DateUtil.getReportDate(dt));

            for (DayLineDO d : dayLineDOS) {

                FinanceDO fs = fsMap.get(d.getCode());
                //financeDOMapper.selectByCode(d.getCode(), 1, SEASON);
                BasicDO basic = basicDOMapper.selectByCode(d.getCode());
                searchVOS.add(SearchVO.convert(d, fs, basic));

            }

            /**
             * 放到此处不够优雅
             */
            if (StringUtils.equals(latestDT, dt)) {
                cacheNewsVO();
            }

            return searchVOS;
        }

    }

    private SearchVO getOneByCodeAndDt(String code, String dt) {

        List<DayLineDO> dayLineDOS = dayLineDOMapper.selectByCond(new SearchRequest(code, dt));
        if (dayLineDOS.size() == 0) {
            return null;
        }
        List<FinanceDO> financeDOS = financeDOMapper.selectByCond(new FinRequest(code, DateUtil.getReportDate(dt)));
        BasicDO basic = basicDOMapper.selectByCode(code);

        return SearchVO.convert(dayLineDOS.get(0), financeDOS.size() == 0 ? null : financeDOS.get(0), basic);


    }


    private Map<String, FinanceDO> getFinanceMap(String finDate) {
        Map<String, FinanceDO> map = new HashMap<>();
        FinRequest finRequest = new FinRequest();
        finRequest.setFinDate(finDate);
        finRequest.setFinType(SEASON);
        finRequest.setPageNo(1);
        finRequest.setPageSize(Integer.MAX_VALUE);
        List<FinanceDO> fs = financeDOMapper.selectByCond(finRequest);
        for (FinanceDO f : fs) {
            map.put(f.getCode(), f);
        }
        return map;

    }


    public List<ShapeVO> queryShape(String code, int days) {

        List<DayLineDO> ds = dayLineDOMapper.selectByCode(code, days);

        if (ds.size() == 0) {
            return new ArrayList<>();
        } else {
            return ds.stream().map(p -> new ShapeVO(p)).collect(Collectors.toList());
        }

    }


    public Map<String, List<ProfitVO>> queryBasicInfo(String code, int days) {

        Map<String, List<ProfitVO>> map = new HashMap<>();


        List<FinanceDO> fs = financeDOMapper.selectByCode(code, days, SEASON);

        if (fs.size() > 0) {
            List ss = fs.stream().map(p -> new ProfitVO(p)).collect(Collectors.toList());
            map.put(IndexTypeConstant.FIN_SEASON, ss);
        }

        fs = financeDOMapper.selectByCode(code, days, YEAR);
        if (fs.size() > 0) {
            List ss = fs.stream().map(p -> new ProfitVO(p)).collect(Collectors.toList());
            map.put(IndexTypeConstant.FIN_YEAR, ss);
        }
        return map;

    }


    public List<TechVO> queryTechData(String code, int days) {

        List<DayLineDO> ds = dayLineDOMapper.selectByCode(code, days);
        if (ds.size() > 0) {
            List ss = ds.stream().map(p -> new TechVO(p)).collect(Collectors.toList());
            return ss;
        }
        return new ArrayList<>();
    }


    public Map<String, Object> queryOrgTrend(String code, int fdays, int kdays) {

        Map<String, Object> map = new HashMap<>();


        List<FinanceDO> fs = financeDOMapper.selectByCode(code, fdays, SEASON);

        if (fs.size() > 0) {
            List ss = fs.stream().map(p -> new OrgTrendVO(p)).collect(Collectors.toList());
            map.put(IndexTypeConstant.ORG_TREND_FIN, ss);
        }

        List<DayLineDO> ds = dayLineDOMapper.selectByCode(code, kdays);
        if (ds.size() > 0) {
            List ss = ds.stream().map(p -> new OrgTrendDayVO(p)).collect(Collectors.toList());
            map.put(IndexTypeConstant.ORG_TREND_DAY, ss);
        }
        return map;

    }


    private void cacheNewsVO() {

        if (latestDT != null) {
            List<SearchVO> vos = searchByDate(latestDT);
            for (SearchVO vo : vos) {
                latestVO.put(vo.getCode(), vo);
            }
        }
    }


    public SearchVO getFromCacheNewsVO(String code) {

        SearchVO vo = latestVO.get(code);
        if (vo == null) {
            return getOneByCodeAndDt(code, latestDT);
        }
        return vo;

    }


    @Override
    public void afterPropertiesSet() throws Exception {

        Date dt = dayLineDOMapper.selectNewestDate();
        if (dt != null) {
            latestDT = DateUtil.getShortFormat(dt);
        }
    }
}
