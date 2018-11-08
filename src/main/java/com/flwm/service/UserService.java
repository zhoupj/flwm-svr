package com.flwm.service;

import com.flwm.common.domain.ErrorCodeEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.DateUtil;
import com.flwm.common.util.LogUtil;
import com.flwm.dal.dao.ActivityDO;
import com.flwm.dal.dao.BuyRecordDO;
import com.flwm.dal.dao.UserDO;
import com.flwm.dal.mapper.ActivityDOMapper;
import com.flwm.dal.mapper.BuyRecordDOMapper;
import com.flwm.dal.mapper.UserDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by zhoupj on 10/27/18.
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    UserDOMapper userDOMapper;
    @Autowired
    BuyRecordDOMapper buyRecordDOMapper;
    @Autowired
    ActivityDOMapper activityDOMapper;

    public UserDO getUser(String openId) {
        return userDOMapper.selectByOpenId(openId);
    }


    public UserDO getUser(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }


    public void saveUser(String openId, String nickName) {

        UserDO userDO = new UserDO();
        userDO.setOpenId(openId);
        userDO.setName(nickName);
        userDO.setThisLoginTime(new Date());
        userDO.setLoginDays(1);
        userDO.setIsMember(0);
        userDOMapper.insertSelective(userDO);
    }

    public UserDO flushUserInfo(Integer id) {

        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        userDO.setLastLoginTime(userDO.getThisLoginTime());
        userDO.setThisLoginTime(new Date());

        if (userDO.getLastLoginTime() != null) {
            long diff = userDO.getThisLoginTime().getTime() - userDO.getLastLoginTime().getTime();
            if (diff > 24 * 3600000) {
                userDO.setLoginDays(userDO.getLoginDays() + 1);
            }
        }

        if (userDO.getIsMember() == 1 && userDO.getMemberDeadline() != null) {
            if (userDO.getMemberDeadline().compareTo(new Date()) < 0) {
                userDO.setIsMember(0);
                userDO.setMemberDeadline(null);
            }
        }
        userDOMapper.updateByPrimaryKey(userDO);

        return userDO;
    }


    public void suggest(Integer userId,String suggest){
        LogUtil.log(LogUtil.SUGGEST,userId,suggest);
    }

    @Transactional
    public void buyMember(Integer userId, Integer actId) {

        UserDO userDO = getUser(userId);
        if (userDO.getIsMember() == 1) {
            throw new FMException(ErrorCodeEnum.MEMBER_BUY_FORBIDDEN);
        } else {
            ActivityDO act = activityDOMapper.selectByPrimaryKey(actId);

            addBuyRecord(userId,actId);
            updateMember(userDO,act);

            LogUtil.log(LogUtil.BUY, userId, actId, act.getActName());
        }

    }

    private void updateMember(UserDO userDO,ActivityDO act){
        userDO.setIsMember(1);
        userDO.setMemberDeadline(DateUtil.getDayByCode(act.getActCode()));
        userDOMapper.updateByPrimaryKey(userDO);
    }



    private void addBuyRecord(Integer userId,Integer actId){
        BuyRecordDO buyRecordDO = new BuyRecordDO();
        buyRecordDO.setActId(actId);
        buyRecordDO.setUserId(userId);
        buyRecordDO.setBuyDate(new Date());
        buyRecordDO.setIsSucess(1);
        buyRecordDOMapper.insert(buyRecordDO);

    }


}
