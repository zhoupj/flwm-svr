package com.flwm.controller;

import com.flwm.common.util.BaiduRecognizerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/sp")
public class SpeechController {


    // 设置APPID/AK/SK，注册百度语音识别API即可获取
    public static final String APP_ID = "15141393";
    public static final String API_KEY = "A4PWjfbK5BEUZADUc3x9F3Q7";
    public static final String SECRET_KEY = "iLiDG14UT6Is3oTur6sdaS7dZ2IGwj2s";

    /**
     * @Description TODO
     * @return
     * @author liuyang
     * @blog http://www.pqsky.me
     * @date 2018年1月27日
     */
    @RequestMapping(value = "/convert")
    public String convert(HttpServletRequest request) {
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");

        try{
            return BaiduRecognizerUtil.convert(file.getInputStream());
        }catch (Exception e){
            return "";
        }

    }



}
