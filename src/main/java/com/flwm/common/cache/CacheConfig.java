package com.flwm.common.cache;


import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configurable
@EnableCaching
public class CacheConfig {


    public static final String userCache = "userCache";
    public static final String shareCode = "shareCode";
    public static final String shareDate = "shareDate";


    public enum CacheEnum {


        USER_CACHE(userCache, 500, 7200), //有效期5秒
        SHARE_CODE(shareCode, 6000, 36000), //缺省10秒
        SHARE_DATE(shareDate, 30, 7200),

        ;

        @Getter
        private String name;
        @Getter
        private int maxSize;    //最大數量
        @Getter
        private int ttl;        //过期时间（秒）


        CacheEnum(String name, int ttl, int maxSize) {
            this.name = name;
            this.ttl = ttl;
            this.maxSize = maxSize;
        }


    }


    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {

        /**
         *   https://blog.csdn.net/qq_38398479/article/details/70578876
         */


        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caches = new ArrayList<CaffeineCache>();
        for (CacheEnum c : CacheEnum.values()) {
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }
        cacheManager.setCaches(caches);

        return cacheManager;
    }

}
