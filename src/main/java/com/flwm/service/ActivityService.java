package com.flwm.service;

import com.flwm.dal.dao.ActivityDO;
import com.flwm.dal.mapper.ActivityDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhoupj on 10/28/18.
 */
@Service
public class ActivityService {


    @Autowired
    private ActivityDOMapper activityDOMapper;

    public List<ActivityDO> getAll(){
        return activityDOMapper.selectAll();
    }
}
