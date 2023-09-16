package com.kocesat.project.concurrency.optimisticlock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceTest {

  private final AccountService accountService;

  public void testRaceCondition() {

    Thread[] threads = new Thread[15];
    for (int i = 0; i < threads.length; i++){
      var thread = new Thread(() -> {
        try {
          accountService.withdraw(1L, BigDecimal.TEN);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      });
      threads[i] = thread;
    }

    for (Thread thread : threads) {
      thread.run();
    }
  }
}
