package com.flwm.service;

import com.flwm.common.VO.UserCountVO;
import com.flwm.common.auth.MemberLevelEnum;
import com.flwm.common.cache.CacheConfig;
import com.flwm.common.domain.FMErrorEnum;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        UserDO userDO = userDOMapper.selectByOpenId(openId);
        if (userDO != null) {
            checkMemeber(userDO);
        }
        return userDO;
    }

    public UserDO getUserByPhone(String phone) {
        UserDO userDO = userDOMapper.selectByPhone(phone);
        if (userDO != null) {
            checkMemeber(userDO);
        }
        return userDO;
    }

    public void checkMemeber(UserDO userDO) {
        if (userDO.getIsMember() >= MemberLevelEnum.ONE_LEVEL.getLevel() && userDO.getMemberDeadline() != null) {
            if (userDO.getMemberDeadline().compareTo(new Date()) < 0) {
                userDO.setIsMember(MemberLevelEnum.USER.getLevel());
                userDO.setMemberDeadline(null);
                userDOMapper.updateByPrimaryKey(userDO);
            }

        }
    }


    @Cacheable(value = CacheConfig.userCache, key = "#id", unless = "#result==null")
    public UserDO getUser(Integer id) {
        return userDOMapper.selectByPrimaryKey(id);
    }


    @CacheEvict(value = CacheConfig.userCount, key = "#root.methodName")
    public void insertUser(String openId, String nickName) {

        /**
         * 上一次登录 需要在session
         * 推出
         * //TODO
         */

        UserDO userDO = new UserDO();
        userDO.setOpenId(openId);
        userDO.setName(nickName);
        userDO.setFirstLoginTime(new Date());
        /**
         * 首次赠送7天会员
         */
        userDO.setIsMember(1);
        userDO.setMemberDeadline(DateUtil.getDayByCode("week_v"));
        userDO.setLastLoginTime(userDO.getFirstLoginTime());
        userDOMapper.insertSelective(userDO);
    }

    public void updateLastLoginTime(UserDO userDO) {

        if (userDO != null) {
            userDO.setLastLoginTime(new Date());
            userDOMapper.updateByPrimaryKey(userDO);
        }
    }


    public void suggest(Integer userId, String suggest) {
        LogUtil.log(LogUtil.SUGGEST, userId, suggest);
    }

    @Transactional
    @CacheEvict(value = CacheConfig.userCache, key = "#userId")
    public void buyMember(Integer userId, Integer actId) {

        UserDO userDO = getUser(userId);
        if (userDO.getIsMember() == MemberLevelEnum.SUPER.getLevel()) {
            throw new FMException(FMErrorEnum.MEMBER_BUY_FORBIDDEN);
        } else {
            ActivityDO act = activityDOMapper.selectByPrimaryKey(actId);
            addBuyRecord(userId, actId);
            updateMember(userDO, act);
            LogUtil.log(LogUtil.BUY, userId, actId, act.getActName());

        }

    }


    private void updateMember(UserDO userDO, ActivityDO act) {
        userDO.setIsMember(MemberLevelEnum.SUPER.getLevel());
        userDO.setMemberDeadline(DateUtil.getDayByCode(act.getActCode()));
        userDOMapper.updateByPrimaryKey(userDO);
    }


    private void addBuyRecord(Integer userId, Integer actId) {
        BuyRecordDO buyRecordDO = new BuyRecordDO();
        buyRecordDO.setActId(actId);
        buyRecordDO.setUserId(userId);
        buyRecordDO.setBuyDate(new Date());
        buyRecordDO.setIsSucess(1);
        buyRecordDOMapper.insert(buyRecordDO);

    }

    @CacheEvict(value = CacheConfig.userCache, key = "#userId")
    public void updateUserPhone(Integer userId, String phone) {

        UserDO userDO = getUser(userId);
        userDO.setPhone(phone);
        userDOMapper.updateByPrimaryKeySelective(userDO);

    }


    @Cacheable(value = CacheConfig.userCount, key = "#root.methodName", unless = "#result==null")
    public UserCountVO queryUserCount() {

        long uc = userDOMapper.selectCount();
        long mc = userDOMapper.selectMemberCount();
        return new UserCountVO(uc, mc);
    }


}
