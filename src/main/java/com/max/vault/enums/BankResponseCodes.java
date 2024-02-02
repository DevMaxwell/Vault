package com.max.vault.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum BankResponseCodes {
  USER_EXIST("01", "User already exists"),
  USER_CREATION_SUCCESS("00", "User created successfully");

  private String code;
  private String message;
  BankResponseCodes(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
