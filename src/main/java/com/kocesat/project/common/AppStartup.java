package com.kocesat.project.common;

import com.kocesat.project.outbox.job.OrderEventQueueJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

@Component
@Slf4j
public class AppStartup implements CommandLineRunner {
  private final Scheduler scheduler;

  public AppStartup(Scheduler scheduler) {
    this.scheduler = scheduler;
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Application startup...");
    final JobKey jobKey = JobKey.jobKey("orderEventQueueJob", "outbox");
    if (scheduler.checkExists(jobKey)) {
      scheduler.deleteJob(jobKey);
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
