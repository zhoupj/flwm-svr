package com.flwm.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zhoupj on 10/27/18.
 */
@Component
@Data
public class FlwmConfig {

    @Value("${spring.profiles.active}")
    private  String env;

    @Value("${spring.domain.name}")
    private String domainName;

    @Value("${spring.domain.login}")
    private boolean login;

}
