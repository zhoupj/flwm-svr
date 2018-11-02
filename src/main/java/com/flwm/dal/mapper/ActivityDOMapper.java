package com.flwm.dal.mapper;

import com.flwm.dal.dao.ActivityDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ActivityDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityDO record);

    int insertSelective(ActivityDO record);

    ActivityDO selectByPrimaryKey(Integer id);

    List<ActivityDO> selectAll();

    int updateByPrimaryKeySelective(ActivityDO record);

    int updateByPrimaryKey(ActivityDO record);
}