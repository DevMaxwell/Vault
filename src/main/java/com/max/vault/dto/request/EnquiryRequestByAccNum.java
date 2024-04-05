package com.max.vault.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryRequestByAccNum {
  @NotBlank
  @Pattern(regexp = "[\\d]{10}")
  private String accountNum;
}
