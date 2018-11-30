package com.flwm.web;

import com.alibaba.fastjson.JSON;
import com.flwm.common.VO.ProfitVO;
import com.flwm.common.VO.SearchVO;
import com.flwm.common.VO.ShapeVO;
import com.flwm.common.VO.TechVO;
import com.flwm.common.domain.SearchRequest;
import com.flwm.service.SearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by zhoupj on 10/29/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    public void searchTest() {

        SearchRequest request = new SearchRequest();
        request.setTradeDate("2018-11-29");
        //request.setRps250(87);
        request.setPageNo(0);
        request.setPageSize(50);
        request.setRps50(96.0);
        // request.setDifftohigh250(25.0);
        //request.setFundHolding(1.0);
        //request.setCode("603787");
        List<SearchVO> vos = searchService.search(request);
        Assert.assertTrue(vos.size() > 0);
        System.out.println("size:" + vos.size());
        System.out.println(JSON.toJSONString(vos));


    }

    @Test
    public void searchTest2() {

        SearchRequest request = new SearchRequest();
        request.setTradeDate("2018-10-31");
        //request.setRps250(87);
        request.setPageNo(0);
        request.setPageSize(50);
        // request.setDifftohigh250(25.0);
        //request.setFundHolding(1.0);
        request.setCode("603787");
        List<SearchVO> vos = searchService.search(request);
        Assert.assertTrue(vos.size() > 0);
        System.out.println(JSON.toJSONString(vos));


    }

    @Test
    public void kTest() {

        List<ShapeVO> lst = searchService.queryShape("000860", 60);
        Assert.assertTrue(lst.size() == 60);
        System.out.println(JSON.toJSONString(lst));
    }

    @Test
    public void basicTest() {
        Map<String, List<ProfitVO>> map = searchService.queryBasicInfo("601339", 10);
        Assert.assertTrue(map.size() == 2);
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void techTest() {
        List<TechVO> lst = searchService.queryTechData("000860", 60);
        Assert.assertTrue(lst.size() == 60);
        System.out.println(JSON.toJSONString(lst));

    }

    @Test
    public void testOrg() {
        Map<String, Object> map = searchService.queryOrgTrend("000860", 10, 60);
        Assert.assertTrue(map.size() == 2);
        System.out.println(JSON.toJSONString(map));

    }

}
