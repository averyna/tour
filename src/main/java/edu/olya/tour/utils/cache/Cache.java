package edu.olya.tour.utils.cache;

/**
 * todo нужна документация
 */
public interface Cache {
    /**
     *
     * @param key
     * @param result
     */
    void put(String key, Object result);

    /**
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> T get(String key);
}
