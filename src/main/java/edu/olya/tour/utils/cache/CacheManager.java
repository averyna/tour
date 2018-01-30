package edu.olya.tour.utils.cache;

import edu.olya.tour.utils.cache.impl.ExpirationEntityCache;
import edu.olya.tour.utils.context.WebContextHolder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * The {@code CacheManager} class wraps an object into a run-time generated class,
 * implementing one or more interfaces, that automatically converts every method call to
 * one of those interfaces into a call to the invoke method on a provided implementation
 * of invocation handler.
 *
 * The InvocationHandler make decisions about how to handle the call, making use of the
 * information available at runtime about the method, including annotations, parameter
 * types and the method’s return type.
 *
 * The invoke method of the invocation handler provides additional behaviour to methods
 * marked with {@code CacheConfig} annotation by placing the returned result of such methods
 * into cache and making them available throw the application context.
 */
public class CacheManager {

    /**
     * Method creates an instance of a proxy class based on the interfaces provided with
     * {@code service} object passed to this method and returns generated proxy transferred
     * to the {@code <T>} type defined at run-time
     * @param service an object that provides interfaces for further implementation with proxy
     * @param <T> a run-time type of the object, passed as a parameter
     * @return an instance of {@code Proxy} class for the specified interfaces
     *       transferred to the type {@code <T>}
     */
    public static <T> T wrap(T service) {
        Class[] interfaces = service.getClass().getInterfaces();

        return (T) Proxy.newProxyInstance(
                CacheManager.class.getClassLoader(),
                interfaces,
                new InvocationHandler() {
                    final T originalReference = service;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            Object behindProxy = originalReference;

                            while (Proxy.isProxyClass(behindProxy.getClass())) {
                                try {
                                    Object handler = Proxy.getInvocationHandler(originalReference);
                                    Class handlerClass = handler.getClass();
                                    Field objField = handlerClass.getDeclaredField("originalReference");
                                    objField.setAccessible(true);
                                    behindProxy = objField.get(handler);
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    throw new Throwable(e);
                                }
                            }

                            Method m = behindProxy.getClass().getMethod(method.getName(), method.getParameterTypes());
                            CacheConfig cacheConfig = m.getAnnotation(CacheConfig.class);

                            boolean cacheEnabled = cacheConfig != null;

                            Cache cache = null;

                            String key = null;

                            if (cacheEnabled) {
                                StringBuilder keySb = new StringBuilder(method.getName());
                                if(args != null) {
                                    for (Object s : args) {
                                        keySb.append(s == null ? "null" : s.toString());
                                    }
                                }
                                key = keySb.toString();
                                switch (cacheConfig.scope()) {
                                    case SESSION:
                                        cache = (Cache) WebContextHolder.getSessionContext().getAttribute(Cache.class.getName());
                                        if (cache == null) {
                                            cache = cacheConfig.cacheImpl().newInstance();
                                            WebContextHolder.getSessionContext().setAttribute(Cache.class.getName(), cache);
                                        }
                                        break;
                                    case APPLICATION:
                                    default:
                                        cache = (Cache) WebContextHolder.getApplicationContext().getAttribute(Cache.class.getName());
                                        if (cache == null) {
                                            cache = cacheConfig.cacheImpl().newInstance();
                                            WebContextHolder.getApplicationContext().setAttribute(Cache.class.getName(), cache);
                                        }
                                }

                                Object cachedValue = cache.get(key);

                                if (cachedValue != null) {
                                    return cachedValue;
                                }
                            }
                            Object result = method.invoke(originalReference, args);

                            if (cache != null) {

                                ((ExpirationEntityCache)cache).setExpirationTime(
                                        Long.parseLong(cacheConfig.params()[0].value()));
                                cache.put(key, result);
                            }

                            return result;
                        } catch (Exception e) {
                            throw e;
                        }
                    }
                }
        );
    }
}
