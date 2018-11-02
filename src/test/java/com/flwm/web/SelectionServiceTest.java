package com.flwm.web;

import com.alibaba.fastjson.JSON;
import com.flwm.common.VO.SearchVO;
import com.flwm.service.SelectionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhoupj on 10/31/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SelectionServiceTest {

    @Autowired
    private SelectionService selectionService;

    @Test
    public void addTest() {

        selectionService.updateGroup(5, "000860", Arrays.asList(1, 2, 3));
        List<Integer> list = selectionService.queryGroupsByCode(5, "000860");
        Assert.assertTrue(list.size() == 3);
        selectionService.updateGroup(5, "000860", Arrays.asList(4, 3));
        list = selectionService.queryGroupsByCode(5, "000860");
        Assert.assertTrue(list.size() == 2);

        List<SearchVO> rst = selectionService.queryByUserId(5, 3);
        System.out.println(JSON.toJSONString(rst));


    }
}
