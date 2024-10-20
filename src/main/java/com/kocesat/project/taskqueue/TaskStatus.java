package com.kocesat.project.taskqueue;

import lombok.Getter;

@Getter
public enum TaskStatus {
  INITIAL,
  QUEUED,
  PROCESSING,
  PROCESSED,
  EXPIRED,
  ERROR;
}
