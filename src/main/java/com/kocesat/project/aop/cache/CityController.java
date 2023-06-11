package com.kocesat.project.aop.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/city")
public class CityController {

  private final CityService cityService;

  @GetMapping
  public List<String> getCities() {
    return cityService.getCities();
  }
}
