package com.kocesat.project.concurrency.optimisticlock;

import com.kocesat.project.common.GenericRuntimeException;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

@Service
@RequiredArgsConstructor
public class AccountServiceTest {

  private final AccountService accountService;

  public void testRaceCondition() {
    int n = 15;
    CountDownLatch startSignal = new CountDownLatch(1);
    CountDownLatch endSignal = new CountDownLatch(n);

    for (int i = 0; i < n; i++){
      (new Thread(() -> {
        try {
          startSignal.await();
          accountService.withdraw(1L, BigDecimal.TEN);
        } catch (InterruptedException e) {
          System.out.println(e.getMessage());
          throw new GenericRuntimeException(e.getMessage());
        } catch (OptimisticLockException e) {
          System.out.println(e.getMessage());
        }
        finally {
          endSignal.countDown();
        }
      })).start();
    }

    try {
      startSignal.countDown();
      endSignal.await();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
