package com.kocesat.project.aop.cache;


import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCache {
  String cacheKey();
}
