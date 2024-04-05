package com.max.vault.controller;

import com.max.vault.dto.request.*;
import com.max.vault.dto.response.BankResponse;
import com.max.vault.model.Transaction;
import com.max.vault.service.BankStatementService;
import com.max.vault.service.UserService;
import com.max.vault.utils.AppUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class VaultController {

  private final UserService userService;

  private final BankStatementService bankStatementService;

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
  public BankResponse balanceEnquiry(@RequestBody @Valid EnquiryRequestByAccNum enquiryRequest){
    return userService.balancenquiry(enquiryRequest);
  }

  @GetMapping("/nameEnquiry")
  public String nameEnquiry(@RequestBody EnquiryRequestByEmail enquiryRequest){
    return userService.nameEnquiry(enquiryRequest);
  }

  @GetMapping("/transferFunds")
  public BankResponse fundsTransfer(@RequestBody @Valid CrDrRequest crDrRequest){
    return userService.fundstransfer(crDrRequest);
  }

  @GetMapping("/getBankStatement")
  public List<Transaction> bankStatement(@RequestBody @Valid BankStatement bankStatement){
    return bankStatementService.generateStatement(bankStatement.getAcctNum(), bankStatement.getStartDate(),
        bankStatement.getEndDate());
  }



}
