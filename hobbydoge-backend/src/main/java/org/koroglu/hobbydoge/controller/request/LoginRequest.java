package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class LoginRequest {

  @Email(message = "Email must be valid.")
  @NotNull(message = "Email cannot be empty.")
  private String email;

  @NotNull(message = "Password cannot be empty.")
  private String password;


}
