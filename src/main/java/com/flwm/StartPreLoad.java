package com.flwm;

import com.flwm.common.VO.SearchVO;
import com.flwm.common.util.DateUtil;
import com.flwm.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class StartPreLoad {

    private ExecutorService executorService = Executors.newFixedThreadPool(4);


    @Autowired
    private SearchService searchService;


    public void preLoad() {

        List<String> dates = DateUtil.getDateList(0, 30);


        String uuid = UUID.randomUUID().toString();


        for (String dt : dates) {

            executorService.submit(() -> {

                MDC.put("tracerId", uuid);

                log.info("start to load data for date:" + dt);

                List<SearchVO> vos = searchService.searchByDate(dt);

                log.info("end to load data for date:" + dt + ",size:" + vos.size());

                MDC.remove("tracerId");

            });
        }
    }
}
