package com.flwm.service;

import com.flwm.common.VO.SearchVO;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.domain.SearchRequest;
import com.flwm.common.domain.UserShareRequest;
import com.flwm.common.util.DateUtil;
import com.flwm.common.util.FilterUtil;
import com.flwm.dal.dao.UserShareDO;
import com.flwm.dal.mapper.DayLineDOMapper;
import com.flwm.dal.mapper.UserShareDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhoupj on 10/31/18.
 */
@Service
public class SelectionService {

    @Autowired
    private UserShareDOMapper userShareDOMapper;
    @Autowired
    private SearchService searchService;


    /**
     * 根据用户和分组查询分组中的数据
     */
    public List<SearchVO> queryByUserId(Integer userId, Integer group) {
        List<UserShareDO> ssl = userShareDOMapper.selectByCond(new UserShareRequest(userId, null, group));
        if (ssl.size() == 0) {
            return new ArrayList<>();
        } else {
            List<String> codes = ssl.stream().map(p -> p.getShareCode()).collect(Collectors.toList());

            List<SearchVO> searchVOs = new ArrayList<>();
            codes.forEach(p -> {
                /**
                 * 此处用缓存，可能存在问题
                 */
                SearchVO vo = searchService.getFromCacheNewsVO(p);
                if (vo == null) {
                    /**
                     * 降级策略，不够优雅，只能显示code
                     */
                    vo = new SearchVO();
                    vo.setCode(p);
                }
                searchVOs.add(vo);
            });
            return searchVOs;
        }

    }


    public void add(String code, Integer group, Integer userId) {


        if (!canAdd(userId, group, code)) {
            return;
        }

        UserShareDO userShareDO = new UserShareDO(userId, code, group);
        userShareDOMapper.insert(userShareDO);

    }

    public void remove(String code, Integer group, Integer userId) {

        List<UserShareDO> list = userShareDOMapper.selectByCond(new UserShareRequest(userId, code, group));
        if (list.size() == 0) {
            return;
        } else {
            userShareDOMapper.deleteByPrimaryKey(list.get(0).getId());
        }
    }


    public List<Integer> queryGroupsByCode(Integer userId, String code) {

        List<UserShareDO> ssl = userShareDOMapper.selectByCond(new UserShareRequest(userId, code, null));
        return ssl.stream().map(p -> p.getsGroup()).collect(Collectors.toList());

    }

    public void updateGroup(Integer userId, String code, List<Integer> newGrp) {


        List<UserShareDO> ssl = userShareDOMapper.selectByCond(new UserShareRequest(userId, code, null));

        List<Integer> oldGrp = new ArrayList<>();
        if (ssl.size() > 0) {
            oldGrp = ssl.stream().map(p -> p.getsGroup()).collect(Collectors.toList());
        }

        for (UserShareDO p : ssl) {
            if (!newGrp.contains(p.getsGroup().intValue())) {
                userShareDOMapper.deleteByPrimaryKey(p.getId());
            }
        }

        for (Integer p : newGrp) {
            if (!oldGrp.contains(p.intValue())) {
                UserShareDO userShareDO = new UserShareDO(userId, code, p);
                userShareDOMapper.insert(userShareDO);
            }
        }

    }


    public boolean canAdd(Integer userId, Integer group, String code) {

        List<UserShareDO> list = userShareDOMapper.selectByCond(new UserShareRequest(userId, null, group));
        if (list.size() >= 100) {
            throw new FMException(FMErrorEnum.SEL_ADD_TOO_MANY);
        }
        Object[] arr = list.stream().filter(p -> p.getShareCode().equals(code)).toArray();
        if (arr != null && arr.length > 0) {
            // throw new FMException(FMErrorEnum.SEL_ADD_EXIST);
            return false;
        }
        return true;
    }


}
