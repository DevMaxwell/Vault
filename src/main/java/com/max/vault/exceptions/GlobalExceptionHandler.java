package com.max.vault.exceptions;

import com.max.vault.enums.BankResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

/**
 * Global method exception handler
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  /**
   * Handle bad request
   * @param ex.
   * @return ww.
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public  Map<String, String> handleInvalidArgumentException(MethodArgumentNotValidException ex){
    Map<String, String> errorMap = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach( error ->
          errorMap.put(error.getField(), error.getDefaultMessage())
        );
    return errorMap;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFound.class)
  public  Map<String, String> handleUserNotFoundException(MethodArgumentNotValidException ex){
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("code", BankResponseCodes.ACCOUNT_NOT_EXIST.getMessage());
    errorMap.put("message", ex.getMessage());
    return errorMap;
  }

}
