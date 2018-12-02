package com.flwm.dal.mapper;

import com.flwm.common.domain.SearchRequest;
import com.flwm.dal.dao.DayLineDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface DayLineDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DayLineDO record);

    int insertSelective(DayLineDO record);

    DayLineDO selectByPrimaryKey(Integer id);

    List<DayLineDO> selectByCond(SearchRequest request);

    List<DayLineDO> selectByCode(@Param(value = "code") String code, @Param(value = "days")Integer days);

    int updateByPrimaryKeySelective(DayLineDO record);

    int updateByPrimaryKey(DayLineDO record);

    Date selectNewestDate();
}