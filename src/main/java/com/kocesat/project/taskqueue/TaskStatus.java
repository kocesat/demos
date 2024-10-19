package com.kocesat.project.taskqueue;

import lombok.Getter;

@Getter
public enum TaskStatus {
  INITIAL,
  PROCESSING,
  PROCESSED,
  EXPIRED,
  ERROR;
}
