package com.kocesat.project.outbox.service;

import com.kocesat.project.outbox.model.Order;
import com.kocesat.project.outbox.model.OrderEvent;
import com.kocesat.project.outbox.repository.OrderEventRepository;
import com.kocesat.project.outbox.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class OrderServiceImpl implements OrderService {
  private final OrderRepository orderRepository;
  private final OrderEventRepository orderEventRepository;

  public Order create(Order orderDTO) {
    final Order order = orderRepository.save(orderDTO);
    final OrderEvent orderEvent = OrderEvent.builder()
      .order(order)
      .orderStatus(0)
      .creationTime(LocalDateTime.now())
      .build();
    orderEventRepository.save(orderEvent);
    return order;
  }

  @Override
  @Transactional(readOnly = true)
  public List<OrderEvent> getUnsentOrderEvents(LocalDateTime time) {
    List<Integer> unsentStatuses = List.of(0, 2);
    return orderEventRepository.findByStatusAndCreatedAfter(unsentStatuses, time);
  }

  public void updateStatusAsSuccess(Long orderEventId) {
    orderEventRepository.updateStatus(orderEventId, 1);
  }

  public void updateStatusAsError(Long orderEventId) {
    orderEventRepository.updateStatus(orderEventId, 2);
  }
}
