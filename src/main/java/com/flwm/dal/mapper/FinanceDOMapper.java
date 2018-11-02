package com.flwm.dal.mapper;

import com.flwm.common.domain.FinRequest;
import com.flwm.common.domain.SearchRequest;
import com.flwm.dal.dao.DayLineDO;
import com.flwm.dal.dao.FinanceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface FinanceDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceDO record);

    int insertSelective(FinanceDO record);

    FinanceDO selectByPrimaryKey(Integer id);

    List<FinanceDO> selectByCond(FinRequest request);


    List<FinanceDO> selectByCode(@Param(value = "code") String code, @Param(value = "days")Integer days,@Param(value = "type") Integer type);

    int updateByPrimaryKeySelective(FinanceDO record);

    int updateByPrimaryKey(FinanceDO record);
}