package com.flwm.common.util;

import com.baidu.aip.speech.AipSpeech;
import org.json.JSONObject;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BaiduRecognizerUtil {


    // 设置APPID/AK/SK，注册百度语音识别API即可获取
    public static final String APP_ID = "15141393";
    public static final String API_KEY = "A4PWjfbK5BEUZADUc3x9F3Q7";
    public static final String SECRET_KEY = "iLiDG14UT6Is3oTur6sdaS7dZ2IGwj2s";


    public static String convert(InputStream ins) {

        try {


            byte[] pcmBytes = mp3Convertpcm(ins);

            org.json.JSONObject resultJson = speechBdApi(pcmBytes);

            System.out.println(resultJson.toString());

            if (null != resultJson && resultJson.getInt("err_no") == 0) {
                return resultJson.getJSONArray("result").get(0).toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private static JSONObject speechBdApi(byte[] pcmBytes) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        org.json.JSONObject res = client.asr(pcmBytes, "pcm", 16000, null);
        return res;
    }


    private static byte[] mp3Convertpcm(InputStream mp3Stream) throws Exception {
        // 原MP3文件转AudioInputStream
        AudioInputStream mp3audioStream = AudioSystem.getAudioInputStream(mp3Stream);
        // 将AudioInputStream MP3文件 转换为PCM AudioInputStream
        AudioInputStream pcmaudioStream = AudioSystem.getAudioInputStream(AudioFormat.Encoding.PCM_SIGNED,
                mp3audioStream);
        byte[] pcmBytes = toByteArray(pcmaudioStream);
        pcmaudioStream.close();
        mp3audioStream.close();
        return pcmBytes;
    }




    public static byte[] toByteArray(InputStream input)
            throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static int copy(InputStream input, OutputStream output)
            throws IOException {
        long count = copyLarge(input, output);
        if (count > 2147483647L) {
            return -1;
        }
        return (int) count;
    }

    public static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[4096];
        long count = 0L;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
}
