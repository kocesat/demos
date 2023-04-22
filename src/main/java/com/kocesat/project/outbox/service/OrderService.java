package com.kocesat.project.outbox.service;

import com.kocesat.project.outbox.model.Order;
import com.kocesat.project.outbox.model.OrderEvent;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
  Order create(Order order);
  List<OrderEvent> getUnsentOrderEvents(LocalDateTime time);
  void updateStatusAsSuccess(Long orderEventId);
  void updateStatusAsError(Long orderEventId);
}
