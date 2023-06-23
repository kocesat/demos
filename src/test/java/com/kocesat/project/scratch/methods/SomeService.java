package com.kocesat.project.scratch.methods;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SomeService {

  public SearchInput serve(List<Filter> filters) {
    return prepareSearchInput(filters);
  }

  private SearchInput prepareSearchInput(List<Filter> filters) {
    SearchInput searchInput = SearchInput.initialize();
    setFieldsFromFilters(searchInput, filters);
    validateFields(searchInput);
    return searchInput;
  }

  private void validateFields(SearchInput searchInput) {
    LocalDate start = searchInput.getStartDate();
    LocalDate end = searchInput.getEndDate();
    if (start == null) {
      throw new IllegalArgumentException("startDate cannot be null");
    }
    if (end == null) {
      throw new IllegalArgumentException("endDate cannot be null");
    }
    if (end.isBefore(start)) {
      throw new IllegalArgumentException("endDate cannot be before startDate");
    }
    if (end.isAfter(start.plusMonths(1))) {
      throw new IllegalArgumentException("date range cannot exceed one month!");
    }
  }

  private void setFieldsFromFilters(SearchInput searchInput, List<Filter> filters) {
    filters.forEach(filter -> {
      switch (filter.getKey()) {
        case SOME_STATUS -> setSomeStatus(searchInput, filter);
        case DATE_RANGE -> setDates(searchInput, filter);
        default -> {}
      }
    });
  }

  private void setDates(SearchInput searchInput, Filter filter) {
    if (filter.getValues() != null && filter.getValues().size() > 1) {
      searchInput.setStartDate(LocalDate.parse(filter.getValues().get(0)));
      searchInput.setEndDate(LocalDate.parse(filter.getValues().get(1)));
    }
  }

  private void setSomeStatus(SearchInput searchInput, Filter filter) {
    List<SomeStatus> statusList = new ArrayList<>();
    filter.getValues()
      .forEach(value -> {
        SomeStatus.tryParse(Integer.valueOf(value))
          .ifPresent(status -> statusList.add(status));
      });
  }

}
