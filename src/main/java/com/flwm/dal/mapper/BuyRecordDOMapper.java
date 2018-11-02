package com.flwm.dal.mapper;

import com.flwm.dal.dao.BuyRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BuyRecordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuyRecordDO record);

    int insertSelective(BuyRecordDO record);

    BuyRecordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuyRecordDO record);

    int updateByPrimaryKey(BuyRecordDO record);
}