package com.kocesat.project.scratch.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

class ObjectMapperTests {

  public static final String TR_ZONE = "Europe/Istanbul";
  @Test
  @SneakyThrows
  void objectMapperLocalDateTest() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    var personModel = Person.builder()
      .localDate(LocalDate.now())
      .localDateTime(LocalDateTime.now())
      .zonedDateTime(LocalDateTime.now().atZone(ZoneId.of(TR_ZONE)))
      .build();
    String actual = objectMapper.writeValueAsString(personModel);
    System.out.println(actual);
  }

  @Test
  @SneakyThrows
  void objectMapperLocalDateTimeTest() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
    var isoString = "{\"zonedDateTime\":\"2023-06-23T19:12:57.817205Z\"}";
    Person person = objectMapper.readValue(isoString, Person.class);
    System.out.println(person.getZonedDateTime().withZoneSameInstant(ZoneId.of(TR_ZONE)).toLocalDateTime());
  }
}
