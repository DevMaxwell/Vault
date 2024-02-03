package com.max.vault.service.impl;

import com.max.vault.dto.request.UserRequest;
import com.max.vault.dto.response.AccountInfo;
import com.max.vault.dto.response.BankResponse;
import com.max.vault.enums.BankResponseCodes;
import com.max.vault.enums.Status;
import com.max.vault.model.User;
import com.max.vault.repository.UserRepository;
import com.max.vault.service.UserService;
import com.max.vault.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * UserServiceImpl
 */
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  /**
   * Impl to create a user account
   * @param  userRequest
   * @return BankResponse
   */
  @Override
  public BankResponse createUser(UserRequest userRequest) {

    Boolean userExist = userRepository.existsByEmail(userRequest.getEmail());
    if(Boolean.TRUE.equals(userExist)){
      return BankResponse.builder()
          .responseCode(BankResponseCodes.USER_EXIST.getCode())
          .responseMessage(BankResponseCodes.USER_EXIST.getMessage())
          .build();
    }
    String  accountNumber = AppUtils.generateAccNum();
    User user = User.builder()
        .email(userRequest.getEmail())
        .address(userRequest.getAddress())
        .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
        .firstName(userRequest.getFirstName())
        .accountNumber(accountNumber)
        .address(userRequest.getAddress())
        .gender(userRequest.getGender())
        .accountBalance(BigDecimal.ZERO)
        .lastName(userRequest.getLastName())
        .otherName(userRequest.getOtherName())
        .accountClassDescription(userRequest.getAccountClassDescription())
        .verificationNumber(String.valueOf(UUID.randomUUID()))
        .lastName(userRequest.getLastName())
        .phoneNumber(userRequest.getPhoneNumber())
        .otherName(userRequest.getOtherName())
        .stateOfOrigin(userRequest.getStateOfOrigin())
        .status(String.valueOf(Status.ACTIVE)).build();

    User savedUsed = userRepository.saveAndFlush(user);

    AccountInfo accountInfo = AccountInfo.builder()
        .accountNumber(savedUsed.getAccountNumber())
        .accountName(savedUsed.getFirstName() + " " + savedUsed.getLastName())
        .accountBalance(String.valueOf(savedUsed.getAccountBalance()))
        .build();
    return BankResponse.builder()
        .responseCode(BankResponseCodes.USER_CREATION_SUCCESS.getCode())
        .responseMessage(BankResponseCodes.USER_CREATION_SUCCESS.getMessage())
        .accountInfo(accountInfo).build();
  }
}