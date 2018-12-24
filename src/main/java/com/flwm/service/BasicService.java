package com.flwm.service;

import com.flwm.common.domain.BasicVO;
import com.flwm.dal.dao.BasicDO;
import com.flwm.dal.mapper.BasicDOMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BasicService implements InitializingBean {

    @Autowired
    private BasicDOMapper basicDOMapper;

    private Map<String, BasicDO> codeMap = new HashMap<>();

    private Map<String, BasicDO> nameMap = new HashMap<>();


    public BasicDO queryByCode(String code) {
        return codeMap.get(code);
    }

    public BasicDO queryByName(String name) {
        return nameMap.get(name);
    }


    public List<BasicVO> getAll() {

        List<BasicVO> vos = new ArrayList<>();

        for (String code : codeMap.keySet()) {
            vos.add(new BasicVO(codeMap.get(code)));
        }

        return vos;

    }


    @Override
    public void afterPropertiesSet() throws Exception {


        List<BasicDO> bd = basicDOMapper.selectAll();

        for (BasicDO basicDO : bd) {

            codeMap.put(basicDO.getCode(), basicDO);
            nameMap.put(basicDO.getName(), basicDO);
        }

    }
}
