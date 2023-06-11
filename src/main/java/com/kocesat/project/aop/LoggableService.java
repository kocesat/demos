package com.kocesat.project.aop;

import org.springframework.stereotype.Service;

@Service
public class LoggableService {

  @Log
  public String getValue(){
    return "value";
  }
}
