package com.kocesat.project.lombok;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ResponseModelTest {
  private final ObjectMapper objectMapper = (new ObjectMapper())
    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Test
  @SneakyThrows
  void serialize() {
    final ResponseModel model = ResponseModel.builder()
      .returnCode("01")
      .returnMessage("Successful")
      .hasNextPage(true)
      .build();
    final String actual = objectMapper.writeValueAsString(model);
    System.out.println(actual);
    final String expected = "{\"returnCode\":\"01\",\"returnMessage\":\"Successful\",\"hasNextPage\":true}";
    Assertions.assertThat(actual).isEqualTo(expected);
  }

  @Test
  @SneakyThrows
  void deserialize() {
    final ResponseModel expected = ResponseModel.builder()
      .returnCode("01")
      .returnMessage("Successful")
      .hasNextPage(true)
      .build();
    final String json = "{\"returnCode\":\"01\",\"returnMessage\":\"Successful\",\"hasNextPage\":true}";
    final ResponseModel actual = objectMapper.readValue(json, ResponseModel.class);
    System.out.println(actual);
    Assertions.assertThat(actual).isEqualTo(expected);
  }
}
