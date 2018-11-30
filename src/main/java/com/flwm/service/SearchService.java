package com.flwm.service;

import com.flwm.common.VO.*;
import com.flwm.common.cache.CacheConfig;
import com.flwm.common.constant.IndexTypeConstant;
import com.flwm.common.domain.SearchRequest;
import com.flwm.dal.dao.BasicDO;
import com.flwm.dal.dao.DayLineDO;
import com.flwm.dal.dao.FinanceDO;
import com.flwm.dal.mapper.BasicDOMapper;
import com.flwm.dal.mapper.DayLineDOMapper;
import com.flwm.dal.mapper.FinanceDOMapper;
import com.flwm.dal.mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhoupj on 10/28/18.
 */
@Service
public class SearchService {


    private static final int SEASON = 1;
    private static final int YEAR = 0;

    @Autowired
    private BasicDOMapper basicDOMapper;
    //    @Autowired
//    private SearchMapper searchMapper;
    @Autowired
    private DayLineDOMapper dayLineDOMapper;
    @Autowired
    private FinanceDOMapper financeDOMapper;


    public List<SearchVO> search(SearchRequest request) {

        // List<SearchVO> vos = searchMapper.selectByCond(request);
        // return mergeWithBasicInfo(vos);

        List<SearchVO> searchVOS = searchByDate(request.getTradeDate());
        searchVOS = filter(searchVOS, request);

        int offset = request.getOffset();

        if (offset >= searchVOS.size()) {
            return new ArrayList<>();
        }

        if (offset + request.getPageSize() >= searchVOS.size()) {
            return searchVOS.subList(offset, searchVOS.size());
        } else {
            return searchVOS.subList(offset, offset + request.getPageSize());
        }


    }

    public Long searchCount(SearchRequest request) {
        // return searchMapper.selectCountByCond(request);

        List<SearchVO> searchVOS = searchByDate(request.getTradeDate());

        searchVOS = filter(searchVOS, request);

        return (long) searchVOS.size();
    }


    @Cacheable(value = CacheConfig.shareDate, key = "#dt", unless = "#result==null || result.size()==0")
    public List<SearchVO> searchByDate(String dt) {

        List<SearchVO> searchVOS = new ArrayList<>();
        SearchRequest dayLineSearch = new SearchRequest();
        dayLineSearch.setTradeDate(dt);
        dayLineSearch.setPageNo(1);
        dayLineSearch.setPageSize(Integer.MAX_VALUE);
        List<DayLineDO> dayLineDOS = dayLineDOMapper.selectByCond(dayLineSearch);

        for (DayLineDO d : dayLineDOS) {

            List<FinanceDO> fs = financeDOMapper.selectByCode(d.getCode(), 1, SEASON);
            BasicDO basic = basicDOMapper.selectByCode(d.getCode());
            searchVOS.add(SearchVO.convert(d, fs.size() == 0 ? null : fs.get(0), basic));

        }
        return searchVOS;
    }


    private List<SearchVO> filter(List<SearchVO> searchVOS, SearchRequest request) {

        return searchVOS.parallelStream()
                .filter(p -> compareLessEqual(request.getDifftohigh250(), p.getDifftohigh250()))
                .filter(p -> compareLessEqual(request.getDifftohigh120(), p.getDifftohigh120()))
                .filter(p -> compareLessEqual(request.getFluof250d(), p.getFluof250d()))
                .filter(p -> compareGreaterEqual(request.getFundHolding(), p.getFundHolding()))
                .filter(p -> compareGreaterEqual(request.getHkHoldingAmount(), p.getHkHoldingAmount()))
                .filter(p -> request.getIsMR() == null || p.getIsMR() != null && p.getIsMR() == request.getIsMR())
                .filter(p -> compareGreaterEqual(request.getRps250(), p.getRps250()))
                .filter(p -> compareGreaterEqual(request.getRps120(), p.getRps120()))
                .filter(p -> compareGreaterEqual(request.getRps50(), p.getRps50()))
                .filter(p -> compareGreaterEqual(request.getSsr2(), p.getSsr2()))
                .filter(p -> compareLessEqual(request.getTurn(), p.getTurn()))
                .filter(p -> request.getGy() == null ||  p.getMa250()!=null && p.getClose() >= p.getMa250())
                .sorted(Comparator.comparing(SearchVO::getCode)).collect(Collectors.toList());
    }


    private boolean compareGreaterEqual(Double request, Double actual) {

        return request == null || (actual != null && actual.doubleValue() >= request);

    }

    private boolean compareLessEqual(Double request, Double actual) {

        return request == null || (actual != null && actual.doubleValue() <= request);
    }




    /*private List<SearchVO> mergeWithBasicInfo(List<SearchVO> vos) {

        for (SearchVO v : vos) {
            BasicDO basic = basicDOMapper.selectByCode(v.getCode());
            v.fillBasicInfo(basic);
        }
        return vos;
    }
*/

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


}
