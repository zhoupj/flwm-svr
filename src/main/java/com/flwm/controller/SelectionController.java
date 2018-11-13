package com.flwm.controller;

import com.flwm.common.VO.SearchVO;
import com.flwm.common.cache.UserCache;
import com.flwm.common.domain.SearchRequest;
import com.flwm.dal.dao.UserDO;
import com.flwm.service.SearchService;
import com.flwm.service.SelectionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoupj on 10/31/18.
 */
@RestController
@RequestMapping(value = "/sel" ,method = RequestMethod.POST)
public class SelectionController {


    @Autowired
    private SelectionService selectionService;


    @PostMapping("/data")
    public List<SearchVO> query(@RequestParam(value ="group") Integer group){
        return selectionService.queryByUserId(UserCache.getUserId(),group);
    }


    @PostMapping("/group")
    public List<Integer> edit(@RequestParam(value ="code") String code) {

       return selectionService.queryGroupsByCode(UserCache.getUserId(),code);

    }

    @PostMapping("/edit")
    public Boolean edit(@RequestParam(value ="code") String code,@RequestParam(value ="gs") String gs) {

        List<Integer> groups=new ArrayList<>();
        String[] arr=gs.split(",");
        for(String s:arr){
            groups.add(Integer.valueOf(s));
        }
        selectionService.updateGroup(UserCache.getUserId(),code,groups);

        return true;

    }

}
