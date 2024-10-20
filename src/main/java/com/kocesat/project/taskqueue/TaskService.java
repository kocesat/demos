package com.kocesat.project.taskqueue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(
    isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
public class TaskService {

  private final TaskRepo taskRepo;

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public int enqueueTaskInNewTx(Long id, String currentStatus, String newStatus, String workerId) {
    return taskRepo.updateStatusAndWorker(id, currentStatus, newStatus, workerId);
  }

  public List<Task> getTaskListForExecution(String currentStatus, LocalDateTime insertStartTime) {
    return taskRepo.findAllForProcess(currentStatus, insertStartTime, 0, 10);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean execute(String workerId) {
    Optional<Task> taskOpt = lockNextTask(TaskStatus.QUEUED, workerId);
    if (taskOpt.isEmpty()) {
      return false;
    }
    final Task currentTask = taskOpt.get();
    log.info("Starting to update task: " + currentTask);
    try {
      Thread.sleep(10_000L);
      updateAs(currentTask.getId(), TaskStatus.PROCESSED.name());
      log.info("Updated task as success: " + currentTask);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      updateAs(currentTask.getId(), TaskStatus.ERROR.name());
    }
    return true;
  }

  private Optional<Task> lockNextTask(TaskStatus status, String workerId) {
    List<Task> taskList = taskRepo.selectForUpdate(status.name(), workerId);
    if (taskList == null || taskList.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(taskList.get(0));
  }

  public int updateAs(Long id, String newStatus) {
    return taskRepo.updateStatus(id, newStatus);
  }
}
