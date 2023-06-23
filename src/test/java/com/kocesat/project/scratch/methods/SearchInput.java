package com.kocesat.project.scratch.methods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchInput {
  private LocalDate startDate;
  private LocalDate endDate;
  private List<SomeStatus> statuses;

  public static SearchInput initialize() {
    LocalDate today = LocalDate.now();
    return SearchInput.builder()
      .startDate(today.minusMonths(1))
      .endDate(today)
      .statuses(Collections.emptyList())
      .build();
  }
}
