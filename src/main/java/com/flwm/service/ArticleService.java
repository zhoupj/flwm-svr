package com.flwm.service;

import com.flwm.dal.dao.ArticleDO;
import com.flwm.dal.dao.ArticleDOWithBLOBs;
import com.flwm.dal.mapper.ArticleDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhoupj on 10/28/18.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDOMapper articleDOMapper;

    public List<ArticleDO> query(int pageNo, int pageSize){

        if(pageNo<0){
            pageNo=0;
        }
        if(pageSize<0 || pageSize>20){
            pageSize=10;
        }
        return articleDOMapper.selectByPage(pageNo*pageSize,pageSize);

    }

    public ArticleDOWithBLOBs queryDetail(int id){
        return articleDOMapper.selectByPrimaryKey(id);

    }
}