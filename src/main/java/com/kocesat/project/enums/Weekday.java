package com.kocesat.project.enums;

public enum Weekday {
  MONDAY(1),
  TUESDAY(2),
  WEDNESDAY(3),
  THURSDAY(4),
  FRIDAY(5),
  SATURDAY(6),
  SUNDAY(7);

  private int code;
  Weekday(int code) {
    this.code = code;
  }

  public int code() {
    return code;
  }
}
