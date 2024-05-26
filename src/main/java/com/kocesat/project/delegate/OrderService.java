package com.kocesat.project.delegate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class OrderService {

  private final OrderProcessHandler orderProcessHandler;

  @Autowired
  public OrderService(@Value("${cloudServices.persist}") boolean cloudPersist) {
    if (cloudPersist) {
      orderProcessHandler = this::saveBoth;
    } else {
      orderProcessHandler = this::persistToDb;
    }
  }

  public CompletableFuture<Void> process(Order order) {
    LocalDateTime entryTime = LocalDateTime.now();
    log.info("An order came at: " + entryTime);
    return orderProcessHandler.handle(order);
  }

  private CompletableFuture<Void> saveBoth(Order order) {
    persistToDb(order);
    writeToBlobStorage(order);
    return new CompletableFuture<>();
  }

  private CompletableFuture<Void> persistToDb(Order order){
    log.info("Persisting to Db...");
    return new CompletableFuture<>();
  }

  private CompletableFuture<Void> writeToBlobStorage(Order order) {
    log.info("Writing to blob storage...");
    return new CompletableFuture<>();
  }
}
