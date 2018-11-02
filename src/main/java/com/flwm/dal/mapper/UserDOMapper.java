package com.flwm.dal.mapper;

import com.flwm.dal.dao.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer id);

    UserDO selectByOpenId(String openId);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKeyWithBLOBs(UserDO record);

    int updateByPrimaryKey(UserDO record);
}