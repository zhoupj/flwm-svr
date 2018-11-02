package com.flwm.controller;

import com.flwm.dal.dao.ArticleDO;
import com.flwm.dal.dao.ArticleDOWithBLOBs;
import com.flwm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zhoupj on 10/28/18.
 */
@RestController
@RequestMapping("/art")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")
    public List<ArticleDO> getArts(@RequestParam(value = "pn") Integer pn,
                                   @RequestParam(value = "sz") Integer sz) {

        return articleService.query(pn, sz);

    }

    @RequestMapping("/detail")
    public ArticleDOWithBLOBs getDetail(@RequestParam(value = "id") Integer id) {

        return articleService.queryDetail(id);

    }

}
