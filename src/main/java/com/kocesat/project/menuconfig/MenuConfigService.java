package com.kocesat.project.menuconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MenuConfigService {

  private final MenuConfigRepository menuConfigRepository;

  public MenuResponse getMenu(String type, String env) {

    List<MenuConfig> menuConfigItems = menuConfigRepository.findByTypeAndEnvironment(type, env);

    Map<String, MenuItemDto> menuItemMap = new HashMap<>();
    for (MenuConfig menuConfigItem : menuConfigItems) {
      menuItemMap.put(menuConfigItem.getKey(), MenuItemDto.builder()
          .name(menuConfigItem.getNameTr())
          .link(menuConfigItem.getLink())
          .children(new ArrayList<>())
          .build());
    }

    List<MenuItemDto> menuItemList = new ArrayList<>();
    for (MenuConfig menuConfigItem : menuConfigItems) {
      MenuItemDto itemDto = mapToMenuItemDto(menuConfigItem, menuItemMap);
      if (itemDto != null) {
        menuItemList.add(itemDto);
      }
    }

    return MenuResponse.builder()
        .menuType(type)
        .menuItems(menuItemList)
        .build();

  }

  private MenuItemDto mapToMenuItemDto(MenuConfig menuConfig, Map<String, MenuItemDto> menuItemMap) {
    MenuItemDto currentItem = menuItemMap.get(menuConfig.getKey());
    currentItem.setName(menuConfig.getNameTr());

    if (menuConfig.getParentKey() != null && menuItemMap.containsKey(menuConfig.getParentKey())) {
      MenuItemDto parentItem = menuItemMap.get(menuConfig.getParentKey());
      parentItem.getChildren().add(currentItem);
      return null;
    }

    return currentItem;
  }
}