package com.kocesat.project.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CacheManagerImpl implements CacheManager {
  private static final int MAX_CACHE_SIZE = 10;
  private final Map<String, String> cache = new LinkedHashMap<>(MAX_CACHE_SIZE, 1);

  @Override
  public Optional<String> get(String key) {
    if (cache.containsKey(key)){
      return Optional.of(cache.get(key));
    }
    return Optional.empty();
  }

  @Override
  public void put(String key, String value) {
    if (cache.size() >= MAX_CACHE_SIZE) {
      String removableKey = (String) cache.keySet().toArray()[0];
      cache.remove(removableKey);
      log.info(String.format("Cache is full. Deleting cache item with key %s", removableKey));
    }
    cache.put(key, value);
  }
}