package edu.olya.tour.utils.cache.impl;

import edu.olya.tour.utils.cache.Cache;

import java.util.HashMap;
import java.util.Map;

public class ExpirationEntityCache implements Cache {
    private long expirationTime = 10000;

    Map<String, ExpirationValue> cacheMap = new HashMap();

    @Override
    public void put(String key, Object result) {
        long now = System.currentTimeMillis();
        long expiredAt = now + expirationTime;
        cacheMap.put(
                key,
                new ExpirationValue(expiredAt, result)
        );
    }

    @Override
    public <T> T get(String key) {
        ExpirationValue value = cacheMap.get(key);
        if (value == null) {
            return null;
        }
        long now = System.currentTimeMillis();
        if (now > value.expiredAt) {
            return null;
        }
        return (T) value.value;
    }

    private static class ExpirationValue {
        private final long expiredAt;
        private final Object value;

        public ExpirationValue(long expirationTime, Object value) {
            this.expiredAt = expirationTime;
            this.value = value;
        }
    }
}
