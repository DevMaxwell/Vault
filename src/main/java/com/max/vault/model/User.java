package com.max.vault.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity: user
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "v_users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String otherName;
  private String gender;
  private String address;
  private String stateOfOrigin;
  private String verificationNumber;
  private String accountNumber;
  private BigDecimal accountBalance;
  private String email;
  private String phoneNumber;
  private String alternativePhoneNumber;
  private String accountClassDescription;
  private String status;
  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime modifiedAt;

}
