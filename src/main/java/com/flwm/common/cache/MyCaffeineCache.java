package com.flwm.common.cache;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;

public class MyCaffeineCache extends CaffeineCache {

    private boolean notAddNullValue = false;

    public MyCaffeineCache(String name, Cache<Object, Object> cache, boolean allNullValue, boolean notAddNullValue) {
        super(name, cache, true);
        this.notAddNullValue = notAddNullValue;
    }

    /**
     * null 不添加到缓存
     * @param key
     * @param value
     */

    public void put(Object key, Object value) {

        if (notAddNullValue && value != null) {
            getNativeCache().put(key, value);
        }

    }
}
