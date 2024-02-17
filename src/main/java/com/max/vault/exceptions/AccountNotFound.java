package com.max.vault.exceptions;

public class UserNotFound extends RuntimeException{
  public UserNotFound(String message) {
    super(message);
  }

  public UserNotFound(String message, Throwable cause) {
    super(message, cause);
  }
}
