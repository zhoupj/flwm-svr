package com.flwm.common.cache;


import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j(topic = "digest")
public class CacheConfig {


    public static final String userCache = "userCache";
    public static final String shareAllCode = "shareCode";
    public static final String shareDate = "shareDate";
    public static final String userCount = "userCount";


    public enum CacheEnum {


        USER_CACHE(userCache, 500, 60 * 60),
        SHARE_CODE(shareAllCode, 1, 60 * 60 * 24),
        SHARE_DATE(shareDate, 30, 60 * 30),
        USER_COUNT_CACHE(userCount, 1, 60 * 5),

        ;

        @Getter
        private String name;
        @Getter
        private int maxSize;    //最大數量
        @Getter
        private int ttl;        //过期时间（秒）


        CacheEnum(String name, int maxSize, int ttl) {
            this.name = name;
            this.maxSize = maxSize;
            this.ttl = ttl;

        }


    }


    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {

        /**
         *   https://blog.csdn.net/qq_38398479/article/details/70578876
         *   https://www.jianshu.com/p/15d0a9ce37dd
         */


        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caches = new ArrayList<>();
        for (CacheEnum c : CacheEnum.values()) {
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterAccess(c.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }
        cacheManager.setCaches(caches);


        new Thread(() -> {
            for (CaffeineCache caff : caches) {
                CacheStats cs = caff.getNativeCache().stats();
                log.info("caffeineCache|" + cs.hitCount() + "|" + cs.missCount() + "|" + cs.hitRate() + "%|" + cs.loadFailureRate() + "%");
                try {
                    Thread.sleep(300000);
                } catch (Throwable e) {

                }
            }

        }).start();

        return cacheManager;
    }

}
