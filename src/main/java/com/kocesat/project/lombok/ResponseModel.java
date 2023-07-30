package com.kocesat.project.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel {

  private String returnCode;
  private String returnMessage;
  @Getter(AccessLevel.NONE)
  private boolean hasNextPage;

  @JsonProperty
  public boolean hasNextPage() {
    return hasNextPage;
  }
}
