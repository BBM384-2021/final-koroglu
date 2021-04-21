package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class VerificationRequest {

  @Email(message = "Email must be valid.")
  @NotNull(message = "Email cannot be empty.")
  private String email;

  @Size(min = 6, max = 6, message = "Verification code must be 6 digit long.")
  @NotNull(message = "Verification code cannot be empty.")
  private Integer verificationCode;

}
