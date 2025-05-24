package com.kocesat.project.common;

import com.kocesat.project.taskqueue.Task;
import com.kocesat.project.taskqueue.TaskService;
import com.kocesat.project.taskqueue.TaskStatus;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
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

    LocalDateTime insertStartTime = LocalDateTime.now().minusDays(10);
    List<Task> taskList = taskService.getTaskListForExecution(TaskStatus.INITIAL.name(), insertStartTime);

    for (Task task : taskList) {
      taskService.enqueueTaskInNewTx(
          task.getId(), task.getStatus(), TaskStatus.QUEUED.name(), "99");
    }

    int chunkSize = 1;
    ForkJoinPool pool = new ForkJoinPool(chunkSize);
    for (int i = 0; i < chunkSize; i++) {
      pool.submit(() -> taskService.execute("99"));
    }
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

