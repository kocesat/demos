package com.kocesat.project.menuconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController {

  private final MenuConfigService menuConfigService;

  @GetMapping
  public MenuResponse getMenu(@RequestParam String type, @RequestParam String env) {
    return menuConfigService.getMenu(type, env);
  }
}
