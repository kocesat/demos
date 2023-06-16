package com.kocesat.project.aop.cache;

import com.kocesat.project.common.Stopwatch;
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

    final String cacheKey = useCache.cacheKey();
    log.info(String.format("Cache control for key: %s", cacheKey));

    if (cache.containsKey(cacheKey)) {
      log.info("Cache hit");
      return cache.get(cacheKey);
    }

    log.info("Cache miss");
    Stopwatch stopwatch = Stopwatch.startNew();
    List<String> cities = (List<String>) joinPoint.proceed();
    stopwatch.stop();

    final String methodName = joinPoint.getSignature().getName();
    log.info(String.format("%s service runtime in ms: %s",
        methodName,
        stopwatch.getElapsedTime()
      ));

    cache.put(cacheKey, cities);
    return cities;
  }
}
