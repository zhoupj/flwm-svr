package com.flwm.controller;

import com.flwm.common.domain.FMErrorEnum;
import com.flwm.common.domain.FMException;
import com.flwm.common.util.BaiduRecognizerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Collection;

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
    @PostMapping(value = "/convert")
    public String convert(HttpServletRequest request) {
        Collection files = ((MultipartHttpServletRequest) request).getFileMap().values();

        if (files == null || files.size() == 0) {
            throw new FMException(FMErrorEnum.SYS_EXCEPTION);
        }

        MultipartFile file = (MultipartFile) files.iterator().next();
        log.info("file name:" + file.getOriginalFilename()+","+file.getContentType()+","+file.getSize());
        try {
            BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            saveFile(file.getInputStream(), file.getName());
            return BaiduRecognizerUtil.convert(bis);
        } catch (Exception e) {
            log.error("parse error", e);
            throw new FMException(FMErrorEnum.SYS_EXCEPTION);
        }

    }

    private void saveFile(InputStream inputStream, String fileName) {

        OutputStream os = null;
        try {
            String path = "./";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件

            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
