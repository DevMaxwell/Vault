package com.max.vault.exceptions;

public class AccountNotFound extends RuntimeException{
  public AccountNotFound(String message) {
    super(message);
  }

  public AccountNotFound(String message, Throwable cause) {
    super(message, cause);
  }
}
