package com.max.vault.utils;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Data
public class PropsReader {

  @Value("${spring.mail.username}")
  private String senderEmail;
}
