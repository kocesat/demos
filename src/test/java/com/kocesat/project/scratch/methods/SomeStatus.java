package com.kocesat.project.scratch.methods;

import java.util.Arrays;
import java.util.Optional;

public enum SomeStatus {
  NEW(0),
  ASSIGNED(1),
  DONE(2);

  private final Integer code;

  SomeStatus(Integer code) {
    this.code = code;
  }

  public static Optional<SomeStatus> tryParse(Integer code) {
    return Arrays.stream(values())
      .filter(item -> item.code.equals(code))
      .findFirst();
  }

  public Integer getCode() {
    return code;
  }
}
