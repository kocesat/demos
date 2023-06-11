package com.kocesat.project.aop.cache;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@Slf4j
public class CachingAspect {

  private static final Map<String, List<String>> cache = new ConcurrentHashMap<>();

  @Around("@annotation(useCache)")
  public Object useCache(ProceedingJoinPoint joinPoint, UseCache useCache) throws Throwable {
    log.info("Before method running...");

    log.info(String.format(
      "Cache kontrol for key: %s",
      useCache.cacheKey()
    ));

    if (cache.containsKey(useCache.cacheKey())) {
      log.info("Cache hit");
      return cache.get(useCache.cacheKey());
    }

    log.info("Cache miss");
    long start = System.currentTimeMillis();
    List<String> cities = (List<String>) joinPoint.proceed();
    long end = System.currentTimeMillis();

    log.info(String.format(
        "%s service runtime in ms: %s",
        joinPoint.getSignature().getName(),
        (end - start)
      ));

    cache.put(useCache.cacheKey(), cities);
    return cities;
  }
}
