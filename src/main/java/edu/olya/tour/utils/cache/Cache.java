package edu.olya.tour.utils.cache;

public interface Cache {

    void put(String key, Object result);

    <T> T get(String key);
}
