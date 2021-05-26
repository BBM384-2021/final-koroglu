package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ResetPasswordRequest {
  @Email(message = "Email must be valid.")
  @NotNull(message = "Email cannot be empty.")
  private String email;

  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must be at least 6 characters long, contain at least 1 letter and 1 number.")
  @NotNull(message = "Password cannot be empty.")
  private String password;

  @Size(min = 6, max = 6, message = "Reset code must be 6 digit long.")
  @NotNull(message = "Reset code cannot be empty.")
  private String token;
}
