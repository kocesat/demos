package com.kocesat.project.taskqueue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

  @Modifying
  @Query(nativeQuery = true,
      value = """
      select * from task_queue t
      where t.status = :status and t.worker_id = :workerId
      order by id
      for update skip locked
      offset 0 rows
      fetch first 1 rows only""")
  List<Task> selectForUpdate(String status, String workerId);

  @Modifying
  @Query("update Task t set t.status = :newStatus, t.updateTime = CURRENT_TIMESTAMP " +
      "where t.id = :id")
  int updateStatus(Long id, String newStatus);

  @Modifying
  @Query("update Task t set t.status = :newStatus, t.updateTime = CURRENT_TIMESTAMP, t.workerId = :workerId " +
      "where t.id = :id and t.status = :currentStatus and t.workerId = '99'")
  int updateStatusAndWorker(Long id, String currentStatus, String newStatus, String workerId);

  @Query(nativeQuery = true,
      value = """
      select * from task_queue t 
      where t.status = :status and t.insert_time >= :insertStartTime
      order by t.id
      offset :offset rows
      fetch first :limit rows only
      """)
  List<Task> findAllForProcess(
      String status, LocalDateTime insertStartTime, int offset, int limit);
}
