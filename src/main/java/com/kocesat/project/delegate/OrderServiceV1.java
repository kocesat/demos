package com.kocesat.project.delegate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class OrderServiceV1 {

  @Value("${orderService.writeToQueue}")
  private boolean writeToQueueEnabled;

  public void process(Order order) {
    LocalDateTime entryTime = LocalDateTime.now();
    log.info("An order came at: " + entryTime);

    this.persistToDb(order);

    if (writeToQueueEnabled) {
      writeToQueue(order);
    }
  }

  private void persistToDb(Order order){
    log.info("Persisting to Db...");
  }

  private void writeToQueue(Order order) {
    log.info("Writing to a queue...");
  }
}
