package com.max.vault.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum BankResponseCodes {
  USER_CREATION_SUCCESS("00", "User created successfully"),
  USER_EXIST("001", "User already exists"),
  ACCOUNT_NOT_EXIST("002","Account does not exist"),
  ACCOUNT_EXIST("003","Account exist");

  private String code;
  private String message;
  BankResponseCodes(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
