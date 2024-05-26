package com.kocesat.project.delegate;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delegate")
public class DelegateController {

  private final OrderService orderService;

  @PostMapping("/order")
  public void createOrder(@RequestBody @Valid Order order) {
    orderService.process(order);
  }
}
