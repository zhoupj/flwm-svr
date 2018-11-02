package com.flwm.dal.mapper;

import com.flwm.dal.dao.BasicDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

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
}