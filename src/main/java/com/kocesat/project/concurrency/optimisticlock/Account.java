package com.kocesat.project.concurrency.optimisticlock;


import com.kocesat.project.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts", schema = "concurrency")
public class Account extends BaseEntity {

  private String accountNo;
  private BigDecimal balance;
}
