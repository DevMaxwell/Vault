package com.max.vault.controller;

import com.max.vault.dto.request.CrDrRequest;
import com.max.vault.dto.request.EnquiryRequest;
import com.max.vault.dto.request.UserRequest;
import com.max.vault.dto.response.BankResponse;
import com.max.vault.service.UserService;
import com.max.vault.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class VaultController {

  private final UserService userService;

  @GetMapping("/hello")
  public String testServerConfig(){
    log.info("tomcat config");
    AppUtils.generateAccNum();
    return "hello and welcome";
  }

  @PostMapping("/createUser")
  public BankResponse createUserAccount(@RequestBody @Valid UserRequest userRequest){
    return userService.createUser(userRequest);
  }

  @GetMapping("/balanceEnquiry")
  public BankResponse balanceEnquiry(@RequestBody @Valid EnquiryRequest enquiryRequest){
    return userService.balancenquiry(enquiryRequest);
  }

  @GetMapping("/nameEnquiry")
  public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest){
    return userService.nameEnquiry(enquiryRequest);
  }

  @GetMapping("/transferFunds")
  public BankResponse fundsTransfer(@RequestBody @Valid CrDrRequest crDrRequest){
    return userService.fundstransfer(crDrRequest);
  }



}
