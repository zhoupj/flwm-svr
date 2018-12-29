package com.flwm.controller;

import com.flwm.common.VO.ProfitVO;
import com.flwm.common.VO.SearchVO;
import com.flwm.common.VO.ShapeVO;
import com.flwm.common.VO.TechVO;
import com.flwm.common.annotation.AuthMember;
import com.flwm.common.annotation.QueryLimit;
import com.flwm.common.auth.MemberLevelEnum;
import com.flwm.common.cache.UserCache;
import com.flwm.common.domain.*;
import com.flwm.common.util.DateUtil;
import com.flwm.common.util.FilterUtil;
import com.flwm.dal.dao.UserDO;
import com.flwm.service.BasicService;
import com.flwm.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.FilterConfig;
import java.lang.reflect.Member;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoupj on 10/29/18.
 */
@RestController
@RequestMapping(value = "/search", method = RequestMethod.POST)
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Autowired
    private BasicService basicService;


    @PostMapping("/list")
    public Result search(@RequestBody SearchRequest request) {

        Assert.isTrue(StringUtils.isNotBlank(request.getTradeDate()), "日期必须填写");

        List<String> validDates = getUseDateList();

        if (!validDates.contains(request.getTradeDate())) {
            throw new FMException(FMErrorEnum.INVALID_QUERY);
        }

        List<SearchVO> vos = searchService.searchByDate(request.getTradeDate());

        vos = FilterUtil.filterData(vos, request);

        int total = vos.size();

        vos = FilterUtil.filterPage(vos, request);

        if (total > 100) {
            throw new FMException(FMErrorEnum.SEARCH_TOO_MANY);
        }
        return Result.succ(vos, total);
    }


    @PostMapping("/data")
    public List<BasicVO> queryCodeNames() {

        return basicService.getAll();
    }

    @PostMapping("/k")
    @AuthMember
    public List<ShapeVO> queryK(@RequestParam(value = "code") String code) {
        return searchService.queryShape(code, 500);
    }


    @PostMapping("/can")
    @QueryLimit
    public boolean canSearchDetail() {
        return true;
    }

    @PostMapping("/basic")
    @AuthMember
    public Map<String, List<ProfitVO>> queryBasic(@RequestParam(value = "code") String code) {
        return searchService.queryBasicInfo(code, 12);
    }

    @PostMapping("/tech")
    @AuthMember
    public List<TechVO> queryTech(@RequestParam(value = "code") String code) {
        return searchService.queryTechData(code, 60);
    }

    @PostMapping("/org")
    @AuthMember
    public Map<String, Object> queryOrg(@RequestParam(value = "code") String code) {
        return searchService.queryOrgTrend(code, 10, 60);
    }

    @PostMapping("/ds")
    public List<String> getUseDateList() {

        UserDO user = UserCache.getUser();
        if (user.getIsMember() == MemberLevelEnum.SUPER.getLevel()) {
            return DateUtil.getDateList(0, 30);
        }
        if (user.getIsMember() == MemberLevelEnum.ONE_LEVEL.getLevel()) {
            return DateUtil.getDateList(0, 5);
        } else {

            String time = DateUtil.getLongFormat(new Date());
            //10店之后可看昨天的
            if (time.substring(11).compareTo("10:00:00") > 0) {
                return DateUtil.getDateList(-1, 3);
            } else {
                return DateUtil.getDateList(-2, 3);
            }

        }

    }


}
