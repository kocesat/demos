package com.kocesat.project.outbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "order_events", schema = "outbox")
@Entity
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;
  private LocalDateTime creationTime;
  private Integer orderStatus;  // 0: Initial, 1: Success, 2: Error
}
