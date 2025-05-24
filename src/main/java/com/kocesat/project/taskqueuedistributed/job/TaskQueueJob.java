package com.kocesat.project.taskqueuedistributed.job;

import com.kocesat.project.taskqueuedistributed.TaskWorkerRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@DisallowConcurrentExecution
@RequiredArgsConstructor
public class TaskQueueJob extends QuartzJobBean {

  private final TaskWorkerRegistry taskWorkerRegistry;

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    Short workerId = taskWorkerRegistry.getWorkerId();

  }

}
