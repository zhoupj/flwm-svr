package com.flwm.dal.mapper;

import com.flwm.common.domain.UserShareRequest;
import com.flwm.dal.dao.UserShareDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserShareDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserShareDO record);

    int insertSelective(UserShareDO record);

    UserShareDO selectByPrimaryKey(Integer id);

    List<UserShareDO> selectByCond(UserShareRequest request);

    int updateByPrimaryKeySelective(UserShareDO record);

    int updateByPrimaryKeyWithBLOBs(UserShareDO record);

    int updateByPrimaryKey(UserShareDO record);
}