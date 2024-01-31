package com.max.vault.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class VaultController {

  @GetMapping("/hello")
  public String testServerConfig(){
    log.info("tomcat config");
    return "hello and welcome";
  }
}
