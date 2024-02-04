package com.max.vault.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class that holds account information
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {
  private String accountName;
  private String accountNumber;
  private String accountBalance;
}
