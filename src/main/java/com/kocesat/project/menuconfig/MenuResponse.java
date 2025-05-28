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
public class MenuResponse {

  private String menuType;
  private List<MenuItemDto> menuItems;
}
