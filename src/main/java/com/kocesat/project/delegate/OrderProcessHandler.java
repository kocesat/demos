package com.kocesat.project.delegate;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface OrderProcessHandler {

  CompletableFuture<Void> handle(Order order);
}
