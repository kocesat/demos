package com.kocesat.project.taskqueuedistributed;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class TaskWorkerRegistry {

  private static final String WORKER_NAME = "TaskWorker-" + UUID.randomUUID();
  private static final long STALE_PERIOD_IN_MINUTES = 1L;
  private final TaskWorkerRepository taskWorkerRepository;
  @PersistenceContext
  private EntityManager entityManager;
  private Short workerId;

  @PostConstruct
  public void register() {
    log.info("Registering task worker with id {}", WORKER_NAME);
    final LocalDateTime now = LocalDateTime.now();
    final LocalDateTime staleThreshold = now.minusMinutes(STALE_PERIOD_IN_MINUTES);
    try {
      String sql = """
        WITH next_available AS (
          SELECT worker_id
          FROM task_worker
          WHERE status = 0 or update_time < :staleThreshold
          ORDER BY worker_id
          LIMIT 1
          FOR UPDATE SKIP LOCKED
        )
        UPDATE task_worker
        SET
            worker_name = :workerName,
            update_time = current_timestamp,
            status = 1
        WHERE worker_id IN (SELECT worker_id FROM next_available)
        RETURNING worker_id;
        """;
      List<Short> result = entityManager.createNativeQuery(sql)
          .setParameter("workerName", WORKER_NAME)
          .setParameter("staleThreshold", staleThreshold)
          .getResultList();

      if (result != null && !result.isEmpty()) {
        workerId = result.get(0);
      }
      log.info("Registered task worker {} with id {}", WORKER_NAME, workerId);

      startHeartbeat();
    } catch (Exception e) {
      log.warn("TaskWorkerRegistry::register failed for: " + WORKER_NAME, e);
    }
  }

  private void startHeartbeat() {
    Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
        () -> {
          if (workerId != null) {
            LocalDateTime now = LocalDateTime.now();
            taskWorkerRepository.heartBeat(WORKER_NAME, workerId, now, Short.valueOf("1"));
          }
        },
        30, 30, TimeUnit.SECONDS
    );
  }

  public Short getWorkerId() {
    return workerId;
  }
}
