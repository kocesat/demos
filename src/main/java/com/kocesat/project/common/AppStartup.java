package com.kocesat.project.common;

import com.kocesat.project.taskqueue.Task;
import com.kocesat.project.taskqueue.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

@Component
@Slf4j
public class AppStartup implements CommandLineRunner {
  private final Scheduler scheduler;
  private final TaskService taskService;

  public AppStartup(Scheduler scheduler, TaskService taskService) {
    this.scheduler = scheduler;
    this.taskService = taskService;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Application startup...");
    final JobKey jobKey = JobKey.jobKey("orderEventQueueJob", "outbox");
    if (scheduler.checkExists(jobKey)) {
      scheduler.deleteJob(jobKey);
    }

    for (int i = 0; i < 1; i++) {
      ForkJoinPool.commonPool().submit(() -> {
        Optional<Task> taskOptional = taskService.selectForUpdate();
        if (taskOptional.isPresent()) {
          Task task = taskOptional.get();
          log.info("Starting to update task: " + task);
          try {
            Thread.sleep(100L);
            taskService.updateAsSuccess(task.getId());
            log.info("UpdatEd task as success: " + task.toString());
          } catch (InterruptedException e) {
            throw new RuntimeException();
          }
        }
      });
    }
//
//    final JobDetail jobDetail = JobBuilder.newJob(OrderEventQueueJob.class)
//      .withIdentity(jobKey.getName(), jobKey.getGroup())
//      .withDescription("Writes orderEvent to the queue")
//      .storeDurably()
//      .build();
//
//    final TriggerKey triggerKey = TriggerKey.triggerKey("orderEventQueueJobTrigger", "outbox");
//    final CronTrigger cronTrigger = TriggerBuilder.newTrigger()
//      .forJob(jobDetail)
//      .withIdentity(triggerKey)
//      .withSchedule(
//        CronScheduleBuilder
//          .cronSchedule("0/5 * * * * ?")
//          .withMisfireHandlingInstructionFireAndProceed()
//          .inTimeZone(TimeZone.getTimeZone("Europe/Istanbul"))
//      )
//      .build();
//
//    scheduler.scheduleJob(jobDetail, cronTrigger);
  }
}
