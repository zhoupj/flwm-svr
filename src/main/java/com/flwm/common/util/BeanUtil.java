package com.flwm.common.util;

import com.flwm.common.domain.FMException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoupj on 10/29/18.
 */
public class BeanUtil {

    public static<T> T convert(Object source, Class<T> targetClazz){

        Assert.notNull(source,"source can't be null");
        Assert.notNull(targetClazz,"tagert class can't be null");

        try{
            T t=targetClazz.newInstance();
            BeanUtils.copyProperties(source,t);
            return t;
        }catch (Exception e){
            throw new FMException("转换到"+targetClazz.getName()+"异常");
        }
    }


    public static<T> List<T> convertList(List sources, Class<T> targetClazz){

        List<T> targetList=new ArrayList<>();

        if(sources!=null){
            for(Object source:sources){
                targetList.add(convert(source,targetClazz));
            }
        }
        return targetList;
    }
}
