package com.kocesat.project.performance.hasduplicate;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

  public static void main(String[] args) throws RunnerException {
    Options options = new OptionsBuilder()
        .include(HasDuplicateBenchmark.class.getSimpleName())
        .forks(1)
        .warmupIterations(3)
        .measurementIterations(10)
        .build();

    new Runner(options).run();
  }
}
