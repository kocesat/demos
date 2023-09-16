package com.kocesat.project.concurrency.optimisticlock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  @Modifying
  @Query("""
    update Account 
      set balance = balance + :amount, 
          updatedAt = current_timestamp 
      where id = :id and updatedAt = :lastVersion
  """)
  int addBalanceWithOptimisticLock(Long id, BigDecimal amount, LocalDateTime lastVersion);
}
