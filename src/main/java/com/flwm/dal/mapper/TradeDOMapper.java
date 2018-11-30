package com.flwm.dal.mapper;

import com.flwm.dal.dao.TradeDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TradeDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TradeDO record);

    int insertSelective(TradeDO record);

    TradeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TradeDO record);

    int updateByPrimaryKey(TradeDO record);

    List<TradeDO> selectByUserId(Integer userId,Integer offset,Integer pageSize);
}