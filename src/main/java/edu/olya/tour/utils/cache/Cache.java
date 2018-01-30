package edu.olya.tour.utils.cache;

/**
 * The interface provides opportunity to put elements into cache or get them from cache.
 * The cache itself should be represented with a container containing key-value pairs.
 * In order to produce put and get operations, the class implementing {@code Cache}
 * interface is supposed to have such container.
 *
 */
public interface Cache {
    /**
     * Method puts {@code result} element into cache with specified {@code key}.
     * @param key a string, representing the {@code result} element in cache.
     * @param result the element to be stored in cache.
     */
    void put(String key, Object result);

    /**
     * Method searches for the presence of the specified {@code key} among keys
     * stored in the cache and returns element associated with this {@code key}.
     * @param key a string, whose presence in the cache is to be tested
     * @param <T> the runtime type of the returned object
     * @return element associated with this {@code key} if {@code key} exists
     * in the cache. Otherwise return {@code null}.
     */
    <T> T get(String key);
}
