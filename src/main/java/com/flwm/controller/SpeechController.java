package com.flwm.controller;

import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.BaiduRecognizerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;

@RestController
@RequestMapping(value = "/sp")
@Slf4j
public class SpeechController {


    // 设置APPID/AK/SK，注册百度语音识别API即可获取
    public static final String APP_ID = "15141393";
    public static final String API_KEY = "A4PWjfbK5BEUZADUc3x9F3Q7";
    public static final String SECRET_KEY = "iLiDG14UT6Is3oTur6sdaS7dZ2IGwj2s";

    /**
     * @return
     * @Description TODO
     * @author liuyang
     * @blog http://www.pqsky.me
     * @date 2018年1月27日
     */
    @RequestMapping(value = "/convert")
    public String convert(HttpServletRequest request) {
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");

        try {
            BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            return BaiduRecognizerUtil.convert(bis);
        } catch (Exception e) {
            log.error("parse error", e);
            throw new FMException(FMErrorEnum.SYS_EXCEPTION);
        }

    }


}
