package com.max.vault.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
  private String transactionType;
  private String transactionStatus;
  private String amount;
  private String sourceAcct;
  private String destAcct;
}
