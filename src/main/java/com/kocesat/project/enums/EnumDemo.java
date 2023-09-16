package com.kocesat.project.enums;

import java.util.Arrays;

public class EnumDemo {
  public static void main(String[] args) {
    int code = 2;
    // parse Weekday from code
    Weekday day = Arrays.stream(Weekday.values())
      .filter(e -> e.code() == code)
      .findFirst()
      .orElseThrow(IllegalArgumentException::new);
    System.out.println(day);
  }
}
