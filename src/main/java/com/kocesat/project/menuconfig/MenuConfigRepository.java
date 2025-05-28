package com.kocesat.project.menuconfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuConfigRepository extends JpaRepository<MenuConfig, Integer> {

  List<MenuConfig> findByTypeAndEnvironment(String type, String env);
}
