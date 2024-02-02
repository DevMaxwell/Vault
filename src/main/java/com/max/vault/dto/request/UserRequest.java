package com.max.vault.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  @NotNull(message = "First Name cannot be null")
  private String firstName;
  @NotNull(message = "Last name cannot be null")
  private String lastName;
  private String otherName;
  @NotNull(message = "Gender cannot be null")
  private String gender;
  @NotNull(message = "Address cannot be null")
  private String address;
  @NotNull(message = "State of origin cannot be null")
  private String stateOfOrigin;
  private String email;
  @NotNull(message = "Phone Number cannot be null")
  private String phoneNumber;
  private String alternativePhoneNumber;
  private String accountClassDescription;
  private String status;
}
