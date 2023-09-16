package com.kocesat.project.concurrency.optimisticlock;

import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService {

  private final AccountRepository repository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void transferMoney(Long id, BigDecimal amount) {
    var fromAccount = getFromAccount(id);

    checkCurrentBalance(amount, fromAccount);

    final LocalDateTime lastVersion = fromAccount.getUpdatedAt();

    final int updatedCount =
      repository.addBalanceWithOptimisticLock(id, amount.negate(), lastVersion);

    if (updatedCount == 0) {
      throw new OptimisticLockException("Account is busy now...");
    }

    System.out.println("Balance updated");
  }

  private static void checkCurrentBalance(BigDecimal amount, Account fromAccount) {
    if (hasInsufficientBalance(amount, fromAccount)) {
      throw new RuntimeException("Insufficient Balance!");
    }
  }

  private Account getFromAccount(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Account not found!"));
  }

  private static boolean hasInsufficientBalance(BigDecimal amount, Account fromAccount) {
    return fromAccount.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0;
  }
}
