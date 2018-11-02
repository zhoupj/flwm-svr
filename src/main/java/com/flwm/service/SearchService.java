package com.flwm.service;

import com.flwm.common.VO.*;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhoupj on 10/28/18.
 */
@Service
public class SearchService {

    @Autowired
    private BasicDOMapper basicDOMapper;
    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private DayLineDOMapper dayLineDOMapper;
    @Autowired
    private FinanceDOMapper financeDOMapper;


    public List<SearchVO> search(SearchRequest request) {

        //TODO 需要集中优化
        List<SearchVO> vos = searchMapper.selectByCond(request);
        return mergeWithBasicInfo(vos);

    }


    public Long searchCount(SearchRequest request){
        return searchMapper.selectCountByCond(request);
    }


    private List<SearchVO> mergeWithBasicInfo(List<SearchVO> vos) {

        for (SearchVO v : vos) {
            BasicDO basic = basicDOMapper.selectByCode(v.getCode());
            v.fillBasicInfo(basic);
        }
        return vos;
    }


    public List<ShapeVO> queryShape(String code, int days){

        List<DayLineDO> ds=dayLineDOMapper.selectByCode(code,days);

        if(ds.size()==0){
            return new ArrayList<>();
        }else{
           return ds.stream().map(p->new ShapeVO(p)).collect(Collectors.toList());
        }

    }


    public Map<String,List<ProfitVO>> queryBasicInfo(String code, int days){

        Map<String,List<ProfitVO>>  map=new HashMap<>();


        List<FinanceDO> fs=financeDOMapper.selectByCode(code,days, 1);

        if(fs.size()>0){
           List ss=  fs.stream().map(p->new ProfitVO(p)).collect(Collectors.toList());
            map.put(IndexTypeConstant.FIN_SEASON,ss);
        }

        fs=financeDOMapper.selectByCode(code,days,0);
        if(fs.size()>0){
            List ss=  fs.stream().map(p->new ProfitVO(p)).collect(Collectors.toList());
            map.put(IndexTypeConstant.FIN_YEAR,ss);
        }
        return map;

    }


    public List<TechVO> queryTechData(String code,int days){

        List<DayLineDO> ds=dayLineDOMapper.selectByCode(code,days);
        if(ds.size()>0){
            List ss=  ds.stream().map(p->new TechVO(p)).collect(Collectors.toList());
            return ss;
        }
        return new ArrayList<>();
    }


    public Map<String,Object> queryOrgTrend(String code, int fdays,int kdays){

        Map<String,Object>  map=new HashMap<>();


        List<FinanceDO> fs=financeDOMapper.selectByCode(code,fdays, 1);

        if(fs.size()>0){
            List ss=  fs.stream().map(p->new OrgTrendVO(p)).collect(Collectors.toList());
            map.put(IndexTypeConstant.ORG_TREND_FIN,ss);
        }

        List<DayLineDO> ds=dayLineDOMapper.selectByCode(code,kdays);
        if(ds.size()>0){
            List ss=  ds.stream().map(p->new OrgTrendDayVO(p)).collect(Collectors.toList());
            map.put(IndexTypeConstant.ORG_TREND_DAY,ss);
        }
        return map;

    }






}
