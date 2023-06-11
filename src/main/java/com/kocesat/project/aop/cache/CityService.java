package com.kocesat.project.aop.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CityService {

  private static final List<String> cities = List.of(
    "Adana", "Ankara", "İstanbul", "Bursa", "Çorum", "..."
  );

  @UseCache(cacheKey = "cities")
  public List<String> getCities() {
    // simulate a long running task
    try {
      Thread.sleep(2300);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return cities;
  }
}
