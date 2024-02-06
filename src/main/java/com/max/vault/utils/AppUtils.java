package com.max.vault.utils;

import lombok.extern.slf4j.Slf4j;
import java.time.Year;

/**
 * utility class for useful
 * method called severally in application
 */
@Slf4j
public class AppUtils {

  public static String generateAccNum(){
    Year year = Year.now();
    int min = 100000;
    int max = 999999;
    int randNum = (int) Math.floor(Math.random() * (max - min)  + min) ;
    String accountNum = String.valueOf(year) + randNum;
    log.info("Account number generate {}", accountNum);
    return accountNum;
  }




}

