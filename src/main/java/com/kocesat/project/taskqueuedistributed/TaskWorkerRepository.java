package com.kocesat.project.taskqueuedistributed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface TaskWorkerRepository extends JpaRepository<TaskWorker, Long> {

  @Transactional(
      rollbackFor = Exception.class,
      isolation = Isolation.READ_COMMITTED,
      propagation = Propagation.REQUIRED)
  @Modifying
  @Query("""
    update TaskWorker tw set tw.status = :status, tw.workerName = :workerName, tw.updateTime = :updateTime
    where tw.status = :status and tw.workerName = :workerName and tw.workerId = :workerId
    """)
  void heartBeat(String workerName, Short workerId, LocalDateTime updateTime, Short status);
}
