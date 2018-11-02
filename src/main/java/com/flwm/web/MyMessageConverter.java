package com.flwm.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.flwm.common.domain.Result;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhoupj on 10/25/18.
 */
public class MyMessageConverter implements HttpMessageConverter {

    static {
        JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    }

    @Override
    public boolean canRead(Class aClass, @Nullable MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class aClass, @Nullable MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.ALL);
    }

    @Override
    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, @Nullable MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {


        String str="";
        httpOutputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        if(!(o instanceof Result)){
            str= JSONObject.toJSONString(Result.succ(o), SerializerFeature.WriteDateUseDateFormat);
        }else{
            str=JSONObject.toJSONString(o,SerializerFeature.WriteDateUseDateFormat);
        }
        StreamUtils.copy(str, Charset.forName("UTF-8"), httpOutputMessage.getBody());
    }
}
