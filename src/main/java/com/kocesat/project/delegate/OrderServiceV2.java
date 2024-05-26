package com.kocesat.project.delegate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class OrderServiceV2 {

  private final OrderProcessHandler orderProcessHandler;

  @Autowired
  public OrderServiceV2(@Value("${orderService.writeToQueue}") boolean writeToQueueEnabled) {
    if (writeToQueueEnabled) {
      orderProcessHandler = order -> this.saveBoth(order);
    } else {
      orderProcessHandler = this::persistToDb;
    }
  }

  public void process(Order order) {
    LocalDateTime entryTime = LocalDateTime.now();
    log.info("An order came at: " + entryTime);
    orderProcessHandler.handle(order);
  }

  private void saveBoth(Order order) {
    persistToDb(order);
    writeToQueue(order);
  }

  private void persistToDb(Order order){
    log.info("Persisting to Db...");
  }

  private void writeToQueue(Order order) {
    log.info("Writing to queue...");
  }
}
