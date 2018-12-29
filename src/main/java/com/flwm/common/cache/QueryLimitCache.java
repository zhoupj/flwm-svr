package com.flwm.common.cache;

import com.flwm.common.auth.MemberLevelEnum;
import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.DateUtil;
import com.flwm.dal.dao.UserDO;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QueryLimitCache {

    private static Map<Integer, LimitCount> userQueryCountMap = new ConcurrentHashMap<>();


    @Data
    static class LimitCount {
        private String day;
        private int count;

        LimitCount(String day, int count) {
            this.day = day;
            this.count = count;
        }
    }


    public static void checkIfCanSearch(UserDO userDO, int limit) {


        Integer userId = userDO.getId();

        LimitCount lc = userQueryCountMap.get(userId);

        if (lc == null) {

            lc = new LimitCount(DateUtil.getTodayDate(), 1);
            userQueryCountMap.put(userId, lc);

        } else if (userDO.getIsMember() == MemberLevelEnum.USER.getLevel()) {

            String today = DateUtil.getTodayDate();

            if (lc.getDay().equals(today) && lc.getCount() > limit) {

                throw new FMException(FMErrorEnum.USER_ACCOUNT_QUERY_LIMIT);

            } else {

                if (lc.getDay().equals(today)) {
                    lc.setCount(lc.getCount() + 1);
                } else {
                    lc.setDay(today);
                    lc.setCount(1);

                }
                userQueryCountMap.put(userId, lc);
            }


        }

    }
}
