package edu.olya.tour.utils.cache.impl;

import edu.olya.tour.utils.cache.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * The class {@code ExpirationEntityCache} represents the cache with objects which are expired by specified
 * timeout. After timeout all expired entities will be overwrited, but not autoremoved,
 * The instance of type {@code ExpirationEntityCache} contains a map of cached objects {@code cacheMap} and
 * provides methods for putting objects to the map and getting objects from it.
 * In order to provide efficiency of cache, an {@code expirationTime} field should be assigned to some value
 * of type {@code long}, before putting an object to the map.
 * The object is being placed to the map of cashed objects with the specified {@code expireAt}
 * property and can be retrieved from the map while the value of {@code expireAt} property exceeds current time.
 */

public class ExpirationEntityCache implements Cache {

    private long expirationTime;

    private Map<String, ExpirationValue> cacheMap = new HashMap();

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

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

    public Object getValue(String key){
        return cacheMap.get(key).value;
    }

    private static class ExpirationValue {
        private final long expiredAt;

        public Object value;

        ExpirationValue(long expirationTime, Object value) {
            this.expiredAt = expirationTime;
            this.value = value;
        }
    }
}
