package com.kocesat.project.performance.hasduplicate;

import org.openjdk.jmh.annotations.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@State(Scope.Thread)
public class HasDuplicateBenchmark {

  @Param({"100", "1000", "10000"})
  private int size;

  @Param({"START", "FIFTY_PERCENT", "END"})
  private Location location;

  private int[] array;

  @Setup
  public void setup() {
    int duplicateIndex = getDuplicateIndex();
    array = IntStream.range(0, size)
        .map(i -> (i == duplicateIndex) ? i : i + 1)
        .toArray();
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public boolean forLoop() {
    Set<Integer> set = new HashSet<>();
    for (int num : array) {
      if (!set.add(num)) {
        return true;
      }
    }
    return false;
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public boolean streamAnyMatch() {
    Set<Integer> set = new HashSet<>();
    return IntStream.of(array).anyMatch(el -> !set.add(el));
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public boolean streamAllMatch() {
    Set<Integer> set = new HashSet<>();
    return !IntStream.of(array).allMatch(set::add);
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public boolean streamDistinct() {
    return IntStream.of(array).distinct().count() != array.length;
  }

  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @OutputTimeUnit(TimeUnit.MILLISECONDS)
  public boolean streamToHashSet() {
    return IntStream.of(array)
        .boxed()
        .collect(Collectors.toSet()).size() != array.length;
  }

  private int getDuplicateIndex() {
    return switch (location) {
      case START -> 1;
      case FIFTY_PERCENT -> (int) Math.floor(size * 0.50);
      case END -> size - 1;
    };
  }
}
