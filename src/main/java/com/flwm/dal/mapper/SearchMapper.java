package com.flwm.dal.mapper;

import com.flwm.common.VO.SearchVO;
import com.flwm.common.domain.SearchRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhoupj on 10/29/18.
 */

@Mapper
@Repository
public interface SearchMapper {


    List<SearchVO> selectByCond (SearchRequest request);

    Long selectCountByCond(SearchRequest request);

}
