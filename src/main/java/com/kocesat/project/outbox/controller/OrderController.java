package com.kocesat.project.outbox.controller;

import com.kocesat.project.outbox.model.Order;
import com.kocesat.project.outbox.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Order> createOrder(@RequestBody @Valid Order orderDTO) {
    final Order order = orderService.create(orderDTO);
    return ResponseEntity.ok(order);
  }

}
