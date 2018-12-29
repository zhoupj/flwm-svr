package com.flwm.util;

import com.flwm.common.util.AliRecognizerUtil;
import com.flwm.common.util.BaiduRecognizerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * SpeechRecognizerDemo class
 * <p>
 * 一句话识别Demo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpeechRecognizerTest {


    @Test
    public void testAliSpeech() throws Exception {

        File file = new File("/Users/zhoupj/Documents/svncode/flwm-web/src/test/java/com/flwm/util/nls-sample-16k.wav");

        AliRecognizerUtil.process(new FileInputStream(file));


    }

    @Test
    public void testBaiduSpeech1() throws Exception {
        File file = new File("/Users/zhoupj/Documents/svncode/flwm-web/src/test/java/com/flwm/util/nls-sample-16k.wav");
        BufferedInputStream zipTest = new BufferedInputStream(new FileInputStream(file));
        String rst = BaiduRecognizerUtil.convert(zipTest);
        System.out.println(rst);
    }


    @Test
    public void testBaiduSpeech2() throws Exception {
        File file=new File("/Users/zhoupj/Documents/svncode/flwm-web/test.mp3");
        BufferedInputStream zipTest = new BufferedInputStream(new FileInputStream(file));
        String rst = BaiduRecognizerUtil.convert(zipTest);
        System.out.println(rst);
    }
}