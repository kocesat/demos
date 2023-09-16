package com.kocesat.project.common;

public final class Comparison<T> {
  private final Comparable<T> first;
  private Comparison(Comparable<T> first) {
    this.first = first;
  }

  public static <T> Comparison<T> compareThat(Comparable<T> first) {
    return new Comparison<>(first);
  }

  public boolean isGreaterThan(T other) {
    if (first == null || other == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    return first.compareTo(other) > 0;
  }

  public boolean isGreaterThanOrEqualTo(T other) {
    if (first == null || other == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    return first.compareTo(other) >= 0;
  }

  public boolean isLessThan(T other) {
    if (first == null || other == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    return first.compareTo(other) < 0;
  }

  public boolean isLessThanOrEqualTo(T other) {
    if (first == null || other == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    return first.compareTo(other) <= 0;
  }

  public boolean isEqualTo(T other) {
    if (first == null || other == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    return first.compareTo(other) == 0;
  }
}