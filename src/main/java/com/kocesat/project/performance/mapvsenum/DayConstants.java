package com.kocesat.project.performance.mapvsenum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class DayConstants {

  private static final Map<String, String> dayMap = new HashMap<>(12);

  static {
    dayMap.put("Monday", "1");
    dayMap.put("Tuesday", "2");
    dayMap.put("Wednesday", "3");
    dayMap.put("Thursday", "4");
    dayMap.put("Friday", "5");
    dayMap.put("Saturday", "6");
    dayMap.put("Sunday", "7");
  }

  public static String parseUsingMap(String code) {
    return dayMap.entrySet().stream()
      .filter(entry -> entry.getValue().equals(code))
      .findFirst()
      .orElseThrow(IllegalArgumentException::new)
      .getKey();
  }

  public enum DayEnum {
    MONDAY("1"),
    TUESDAY("2"),
    WEDNESDAY("3"),
    THURSDAY("4"),
    FRIDAY("5"),
    SATURDAY("6"),
    SUNDAY("7");

    final String code;

    DayEnum(String code) {
      this.code = code;
    }

    public static DayEnum parse(String code) {
      return Arrays.stream(values())
        .filter(dayEnum -> dayEnum.code.equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }
  }
}
