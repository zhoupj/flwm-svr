package com.flwm.dal.mapper;

import com.flwm.common.cache.CacheConfig;
import com.flwm.dal.dao.BasicDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BasicDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasicDO record);

    int insertSelective(BasicDO record);

    BasicDO selectByPrimaryKey(Integer id);

    BasicDO selectByCode(String code);

    int updateByPrimaryKeySelective(BasicDO record);

    int updateByPrimaryKey(BasicDO record);

    //@Cacheable(value = CacheConfig.shareCode,unless = "#result==null || result.size()==0")
    List<String> selectCodes();
}