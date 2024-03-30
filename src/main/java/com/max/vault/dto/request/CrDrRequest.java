package com.max.vault.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CrDrRequest {
  @NotNull(message = "account Number cannot be null")
  @Pattern(regexp = "//d{10}$")
  private String creditAccount;
  @Positive
  private BigDecimal amount;
  @NotNull(message = "account Number cannot be null")
  @Pattern(regexp = "//d{10}$")
  private String debitAccount;
  private String narration;
  @JsonFormat()
  private LocalDateTime transDate;

}
