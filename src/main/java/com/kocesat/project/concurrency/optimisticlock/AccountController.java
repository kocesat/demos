package com.kocesat.project.concurrency.optimisticlock;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account/test/race-condition")
@RequiredArgsConstructor
public class AccountController {

  private final AccountServiceTest testService;

  @GetMapping
  public void testRaceCondition() {
    testService.testRaceCondition();
  }
}
