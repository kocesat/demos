package com.kocesat.project.menuconfig;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "menu_config", schema = "public")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuConfig {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private LocalDateTime insertTime;
  private LocalDateTime updateTime;
  private String type;
  private String key;
  private String description;
  private String nameTr;
  private String nameEn;
  private String keywords;
  private String parentKey;
  private String environment;
  private String permCodes;
  private String link;
}
