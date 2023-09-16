package com.kocesat.project.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableObject {

  @CreatedDate
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @CreatedBy
  @Builder.Default
  @Column(nullable = false)
  private String createdBy = "11111";
  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime updatedAt;
  @LastModifiedBy
  @Builder.Default
  @Column(nullable = false)
  private String updatedBy = "11111";
}
