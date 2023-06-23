package com.kocesat.project.scratch.objectmapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate localDate;
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  private LocalDateTime localDateTime;
  @JsonSerialize(using = ZonedDateTimeSerializer.class)
  private ZonedDateTime zonedDateTime;
}
