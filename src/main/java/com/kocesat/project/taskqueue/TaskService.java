package com.kocesat.project.taskqueue;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
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
  public Optional<Task> selectForUpdate() {
    List<Task> taskList = taskRepo.selectForUpdate();
    if (taskList == null || taskList.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(taskList.get(0));
  }

  public int updateAsSuccess(Long id) {
    return taskRepo.updateAsSuccess(id);
  }
}
