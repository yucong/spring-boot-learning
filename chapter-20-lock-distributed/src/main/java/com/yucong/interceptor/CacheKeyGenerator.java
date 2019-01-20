package com.yucong.interceptor;


import org.aspectj.lang.ProceedingJoinPoint;

/**
 * key生成器
 */
public interface CacheKeyGenerator {

    /**
     * 获取AOP参数,生成指定缓存Key
     *
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
