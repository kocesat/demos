package com.kocesat.project.outbox.job;

import com.kocesat.project.outbox.model.OrderEvent;
import com.kocesat.project.outbox.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@DisallowConcurrentExecution
@RequiredArgsConstructor
public class OrderEventQueueJob extends QuartzJobBean {
  private final OrderService orderService;
  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    try {
      log.info("OrderEventQueueJob started...");
      LocalDateTime creationTimeStart = LocalDate.now().minusDays(2).atStartOfDay();
      List<OrderEvent> orderEvents = orderService.getUnsentOrderEvents(creationTimeStart);
      for (OrderEvent orderEvent : orderEvents) {
        tryCompleteEvent(orderEvent);
      }
    } catch (Exception e) {
      log.error("Unexpected error running OrderEventQueueJob!");
    } finally {
      log.info("OrderEventQueueJob finished...");
    }
  }

  private void tryCompleteEvent(OrderEvent orderEvent) {
    try {
      writeToQueue(orderEvent); // Simulation for writing orderEvent to the quueue
      orderService.updateStatusAsSuccess(orderEvent.getId());
    } catch (Exception e) {
      log.error("Error while handling orderEvent with id: " + orderEvent.getId());
      orderService.updateStatusAsError(orderEvent.getId());
    }
  }

  private void writeToQueue(OrderEvent orderEvent) {
    System.out.println(String.format("Writing orderEvent with id %s to the queue", orderEvent.getId()));
  }
}
