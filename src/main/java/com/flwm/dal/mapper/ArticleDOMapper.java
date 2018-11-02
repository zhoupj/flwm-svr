package com.flwm.dal.mapper;

import com.flwm.dal.dao.ArticleDO;
import com.flwm.dal.dao.ArticleDOWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleDOWithBLOBs record);

    int insertSelective(ArticleDOWithBLOBs record);

    ArticleDOWithBLOBs selectByPrimaryKey(Integer id);

    List<ArticleDO> selectByPage(@Param("offset") Integer offset, @Param("pageSize")Integer pageSize);

    int updateByPrimaryKeySelective(ArticleDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleDOWithBLOBs record);

    int updateByPrimaryKey(ArticleDO record);
}