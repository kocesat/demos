package com.kocesat.project.concurrency.optimisticlock;

import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.kocesat.project.common.Comparison.compareThat;

@Service
@RequiredArgsConstructor
@Transactional(
  propagation = Propagation.REQUIRED,
  isolation = Isolation.READ_COMMITTED,
  rollbackFor = Throwable.class)
@Slf4j
public class AccountService {

  private final AccountRepository repository;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void withdraw(Long id, BigDecimal amount) {
    final Account account = getAccountById(id);
    checkBalanceIsSufficient(account, amount);
    final LocalDateTime lastVersion = account.getUpdatedAt();
    final int updatedCount = repository.addBalanceWithOptimisticLock(id, amount.negate(), lastVersion);
    if (updatedCount == 0) {
      throw new OptimisticLockException("Account is busy now...");
    }
    log.info("Balance is updated");
  }

  private static void checkBalanceIsSufficient(Account fromAccount, BigDecimal subtractAmount ) {
    final BigDecimal updatedBalance = fromAccount.getBalance().subtract(subtractAmount);
    if (compareThat(updatedBalance).isLessThan(BigDecimal.ZERO)) {
      throw new RuntimeException("Insufficient Balance!");
    }
  }

  private Account getAccountById(Long id) {
    return repository.findById(id)
      .orElseThrow(() -> new RuntimeException("Account not found!"));
  }

  private static boolean hasInsufficientBalance(BigDecimal amount, Account fromAccount) {
    return fromAccount.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) < 0;
  }
}
