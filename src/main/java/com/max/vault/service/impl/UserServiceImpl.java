package com.max.vault.service.impl;

import com.max.vault.dto.request.CrDrRequest;
import com.max.vault.dto.request.EmailDetails;
import com.max.vault.dto.request.EnquiryRequest;
import com.max.vault.dto.request.UserRequest;
import com.max.vault.dto.response.AccountInfo;
import com.max.vault.dto.response.BankResponse;
import com.max.vault.enums.BankResponseCodes;
import com.max.vault.enums.Status;
import com.max.vault.exceptions.AccountNotFound;
import com.max.vault.model.User;
import com.max.vault.repository.UserRepository;
import com.max.vault.service.EmailService;
import com.max.vault.service.UserService;
import com.max.vault.utils.AppUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

/**
 * UserServiceImpl
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final EmailService emailService;

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

    // send email
    EmailDetails emailDetails = EmailDetails.builder()
        .messageBody("Thank you for choosing vault. " +
            "Your account has been successfully created." +
            "Your account number is: " + savedUsed.getAccountNumber() +
            "Your account name is: " + savedUsed.getLastName() + " " +
            savedUsed.getFirstName())
        .recipient(savedUsed.getEmail())
        .subject("ACCOUNT CREATION")
        .build();
    emailService.sendEmailAlert(emailDetails);

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

  @Override
  public BankResponse balancenquiry(EnquiryRequest enquiryRequest) {

    User userExist = userRepository.findByAccountNumber(enquiryRequest.getEmail()).orElseThrow(
        () -> new AccountNotFound("Account Number not found")
    );
    return BankResponse.builder()
        .accountInfo(
            AccountInfo.builder()
                .accountBalance(String.valueOf(userExist.getAccountBalance()))
                .accountNumber(userExist.getAccountNumber())
                .accountName(userExist.getFirstName() + " " + userExist.getLastName())
                .build()
        )
        .responseMessage(BankResponseCodes.USER_EXIST.getMessage())
        .responseCode(BankResponseCodes.USER_EXIST.getCode())
        .build();
  }

  @Override
  public String nameEnquiry(EnquiryRequest enquiryRequest) {
    User userExist = userRepository.findByEmail(enquiryRequest.getEmail()).orElseThrow(
        () -> new AccountNotFound("Account Number not found")
    );
    return userExist.getLastName() + " "
        + userExist.getFirstName() + " "
        + userExist.getOtherName();
  }

  @Override
  public BankResponse fundstransfer(CrDrRequest crDrRequest) {
    User creditUser = userRepository.findByAccountNumber(crDrRequest.getCreditAccount()).orElseThrow(
        () -> new AccountNotFound("Credit Account not found")
    );

    User debitUser = userRepository.findByAccountNumber(crDrRequest.getDebitAccount()).orElseThrow(
        () -> new AccountNotFound("Debit Account not found")
    );

    if (debitUser.getAccountBalance().compareTo(crDrRequest.getAmount()) == (-1)){
      BankResponse.builder()
          .accountInfo(
              AccountInfo.builder()
                  .accountBalance(String.valueOf(debitUser.getAccountBalance()))
                  .accountNumber(debitUser.getAccountNumber())
                  .accountName(debitUser.getFirstName() + " " + debitUser.getLastName())
                  .build()
          )
          .responseMessage(BankResponseCodes.INSUF_FUNDS.getMessage())
          .responseCode(BankResponseCodes.INSUF_FUNDS.getCode())
          .build();
    }

    creditUser.setAccountBalance(creditUser.getAccountBalance().add(crDrRequest.getAmount()));
    debitUser.setAccountBalance(debitUser.getAccountBalance().subtract(crDrRequest.getAmount()));

    userRepository.saveAndFlush(creditUser);
    userRepository.saveAndFlush(debitUser);

    EmailDetails debitAlert = EmailDetails.builder()
        .subject("DEBIT ALERT")
        .recipient(debitUser.getEmail())
        .messageBody("A Debit of "+ crDrRequest.getAmount() +
        " occurred in you account. " +
            "Available Balance " + debitUser.getAccountBalance())
        .build();
    emailService.sendEmailAlert(debitAlert);

    EmailDetails creditAlert = EmailDetails.builder()
        .subject("CREDIT ALERT")
        .recipient(debitUser.getEmail())
        .messageBody("A Credit of "+ crDrRequest.getAmount() +
            " occurred in you account. " +
            "Available Balance " + debitUser.getAccountBalance())
        .build();
    emailService.sendEmailAlert(creditAlert);

    return BankResponse.builder()
        .responseMessage(BankResponseCodes.USER_EXIST.getMessage())
        .responseCode(BankResponseCodes.USER_EXIST.getCode())
        .build();
  }

  @Override
  public BankResponse debitAccount(CrDrRequest crDrRequest) {
    return null;
  }
}
