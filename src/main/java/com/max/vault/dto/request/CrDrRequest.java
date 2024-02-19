package com.max.vault.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class CrDrRequest {
  @NotNull(message = "account Number cannot be null")
  @Pattern(regexp = "//d{10}$")
  private String accountNumber;
  private String amount;
  private String destinationAccountNumber;
  private String narration;
  @JsonFormat()
  private LocalDateTime transDate;

}
