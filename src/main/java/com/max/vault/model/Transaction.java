package com.max.vault.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "v_transactions")
public class Transaction {

  @GeneratedValue(strategy = GenerationType.UUID)
  private String transactionId;
  private String transactionType;
  private String transactionStatus;
  private String amount;
  private String sourceAcct;
  private String destAcct;
}
