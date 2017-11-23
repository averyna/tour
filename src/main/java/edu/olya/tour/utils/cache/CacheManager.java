package edu.olya.tour.utils.cache;

import edu.olya.tour.utils.cache.impl.ExpirationEntityCache;
import edu.olya.tour.utils.context.WebContextHolder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CacheManager {

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
                            //можно без while, но если оберток несколько, то без цикла не обойтись
                            while (Proxy.isProxyClass(behindProxy.getClass())) {
                                try {
                                    Object handler = Proxy.getInvocationHandler(originalReference);
                                    Class handlerClass = handler.getClass();
                                    Field objField = handlerClass.getDeclaredField("originalReference");
                                    objField.setAccessible(true);
                                    behindProxy = objField.get(handler);
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }

                            Method m = behindProxy.getClass().getMethod(method.getName(), method.getParameterTypes());
                            CacheConfig cacheConfig = m.getAnnotation(CacheConfig.class);


                            //Returns this element's annotation for the specified type if such an annotation is present, else null.
                            //interface edu.olya.tour.utils.cache.CacheConfig
//                            CacheConfig cacheConfig = method.getAnnotation(CacheConfig.class);
                            boolean cacheEnabled = cacheConfig != null;

                            Cache cache = null;

                            String key = null;

                            if (cacheEnabled) {
                                StringBuilder keySb = new StringBuilder(method.getName());
                                if(args != null) {
                                    for (Object s : args) {
                                        //s.toString вернет имя класса, сущностью которого является аргуметн и хэш-код аргумента
                                        //по сути это "return getClass().getName() + "@" + Integer.toHexString(hashCode());"
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

                                //получаем Object value(список стран) из  "ExpirationValue" fields: long expiredAt; Object value;
                                Object cachedValue = cache.get(key);

                                if (cachedValue != null) {//может быть null, если ExpirationValue==null или now > value.expiredAt
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
