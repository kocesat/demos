package com.kocesat.project.menuconfig;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto {

  private String name;
  private String link;
  private List<MenuItemDto> children;
}
