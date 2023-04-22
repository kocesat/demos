package com.kocesat.project.outbox.repository;

import com.kocesat.project.outbox.model.OrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderEventRepository extends JpaRepository<OrderEvent, Long> {

  @Query("select e from OrderEvent e join fetch e.order " +
    "where e.orderStatus in :statuses and e.creationTime >= :time")
  List<OrderEvent> findByStatusAndCreatedAfter(List<Integer> statuses, LocalDateTime time);

  @Modifying
  @Query(value =
    "update OrderEvent e set e.orderStatus = :newStatus " +
      "where e.id = :id")
  void updateStatus(Long id, Integer newStatus);
}
