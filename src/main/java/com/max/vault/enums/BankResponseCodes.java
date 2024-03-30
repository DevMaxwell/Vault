package com.max.vault.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum BankResponseCodes {
  USER_CREATION_SUCCESS("000", "User created successfully"),
  FUNDS_TRANSFER_SUCCESS("000", "Funds transfer success"),
  USER_EXIST("001", "User already exists"),
  ACCOUNT_NOT_EXIST("002","Account does not exist"),
  ACCOUNT_EXIST("003","Account exist"),
  INSUF_FUNDS("004","Insufficient Funds");

  private String code;
  private String message;
  BankResponseCodes(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
