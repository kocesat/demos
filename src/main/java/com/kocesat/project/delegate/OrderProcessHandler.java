package com.kocesat.project.delegate;

@FunctionalInterface
public interface OrderProcessHandler {

  void handle(Order order);
}
