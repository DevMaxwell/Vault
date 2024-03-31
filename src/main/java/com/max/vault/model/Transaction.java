package com.max.vault.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "v_transactions")
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String transactionId;
  private String transactionType;
  private String transactionStatus;
  private String amount;
  private String sourceAcct;
  private String destAcct;
  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime modifiedAt;
}
