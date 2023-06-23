package com.kocesat.project.scratch.methods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class SmallMethodsTest {

  @Test
  void test_shouldThrowException_whenDateRangeExceedsOneMonth() {
    var service = new SomeService();
    List<Filter> filters = prepareInvalidFilters();
    Assertions.assertThrows(
      IllegalArgumentException.class,
      () -> service.serve(filters),
      "date range cannot exceed one month!"
    );
  }

  @Test
  void test_shouldInitialize_whenNoDateFilterProvided() {
    List<Filter> filters = prepareNoDateFilters();
    var service = new SomeService();
    SearchInput actual = service.serve(filters);
    Assertions.assertEquals(actual.getStartDate(), LocalDate.now().minusMonths(1));
    Assertions.assertEquals(actual.getEndDate(), LocalDate.now());
  }

  private List<Filter> prepareNoDateFilters() {
    return List.of(
      Filter.builder()
        .key(FilterKey.SOME_STATUS)
        .values(List.of("0", "2"))
        .build()
    );
  }

  private List<Filter> prepareInvalidFilters() {
    return List.of(
      Filter.builder()
        .key(FilterKey.SOME_STATUS)
        .values(List.of("0", "2"))
        .build(),
      Filter.builder()
        .key(FilterKey.DATE_RANGE)
        .values(List.of("2022-10-01", "2022-12-13"))
        .build()
    );
  }
}