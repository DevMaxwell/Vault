package com.max.vault.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  @NotBlank(message = "First Name cannot be null")
  private String firstName;
  @NotBlank(message = "Last name cannot be null")
  private String lastName;
  private String otherName;
  @NotBlank(message = "Gender cannot be null")
  private String gender;
  @NotBlank(message = "Address cannot be null")
  private String address;
  @NotBlank(message = "State of origin cannot be null")
  private String stateOfOrigin;
  private String email;
  @NotBlank(message = "Phone Number cannot be null")
  private String phoneNumber;
  private String alternativePhoneNumber;
  private String accountClassDescription;
  private String status;
}
