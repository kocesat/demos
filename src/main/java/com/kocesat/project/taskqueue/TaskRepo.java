package com.kocesat.project.taskqueue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

  @Modifying
  @Query(nativeQuery = true,
      value = """
      select * from task_queue t
      where t.status = 'INITIAL' and t.worker_id = '99'
      for update skip locked
      offset 0 rows
      fetch first 1 rows only""")
  List<Task> selectForUpdate();

  @Modifying
  @Query("update Task t set t.status = 'SUCCESS', t.updateTime = CURRENT_TIMESTAMP " +
      "where t.id = :id")
  int updateAsSuccess(Long id);

}
