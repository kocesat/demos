package com.kocesat.project.common;

public class Stopwatch {
  private long elapsedTime;
  private boolean started;
  private long startTime;
  private long endTime;

  public static Stopwatch startNew() {
    return new Stopwatch().start();
  }

  public Stopwatch start() {
    if (!started) {
      startTime = System.currentTimeMillis();
      started = true;
    }
    return this;
  }

  public Stopwatch stop() {
    if (started) {
      endTime = System.currentTimeMillis();
      started = false;
      elapsedTime = endTime - startTime;
    }
    return this;
  }

  public long getElapsedTime() {
    if (startTime != 0) {
      if (endTime != 0) {
        return elapsedTime;
      }
      return System.currentTimeMillis() - startTime;
    }
    throw new RuntimeException("Stopwatch is not started yet!");
  }
}
