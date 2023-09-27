package com.kocesat.project.performance.mapvsenum;

import com.kocesat.project.common.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoopPerformance {

  private static final long MAX_ITER_COUNT = 10_000_000;

  public static void main(String[] args) {
    var codesWarmUp = createCodes(1_000_000);
    var codes = createCodes(MAX_ITER_COUNT);
    Stopwatch sw = new Stopwatch();
    System.out.println("Enum Iteration warmup");
    sw.start();
    getDaysUsingEnumIteration(codesWarmUp);
    sw.stop();
    System.out.println("Elapsed time in ms: " + sw.getElapsedTime());;
    System.out.println("Map iteration warmup");
    sw.start();
    getDaysUsingMapIteration(codesWarmUp);
    sw.stop();
    System.out.println("Elapsed time in ms : " + sw.getElapsedTime());

    System.out.println("Enum iteration");
    sw.start();
    getDaysUsingEnumIteration(codes);
    sw.stop();
    System.out.println("Elapsed time in ms: " + sw.getElapsedTime());;
    System.out.println("Map iteration");
    sw.start();
    getDaysUsingMapIteration(codes);
    sw.stop();
    System.out.println("Elapsed time in ms : " + sw.getElapsedTime());
  }

  public static List<String> createCodes(long count) {
    Random random = new Random();
    List<String> codes = new ArrayList<>();
    for (long i = 0; i < count; i++) {
      String dayCode = String.valueOf(random.nextInt(1,8));
      codes.add(dayCode);
    }
    return codes;
  }

  public static List<String> getDaysUsingMapIteration(List<String> codes) {
    return codes.stream()
      .map(DayConstants::parseUsingMap)
      .toList();
  }

  public static List<String> getDaysUsingEnumIteration(List<String> codes) {
    return codes.stream()
      .map(code -> DayConstants.DayEnum.parse(code).code)
      .toList();
  }
}
