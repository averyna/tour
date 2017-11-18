package edu.olya.tour.utils.cache;

import edu.olya.tour.utils.cache.impl.ExpirationEntityCache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface CacheConfig {
    Class<? extends Cache> cacheImpl() default ExpirationEntityCache.class;
    Scope scope() default Scope.APPLICATION;
    CacheParam[] params() default {};
}
