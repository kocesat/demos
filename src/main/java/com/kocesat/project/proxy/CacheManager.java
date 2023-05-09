package com.kocesat.project.proxy;

import java.util.Optional;

public interface CacheManager {
  public Optional<String> get(String key);
  public void put(String key, String value);
}
