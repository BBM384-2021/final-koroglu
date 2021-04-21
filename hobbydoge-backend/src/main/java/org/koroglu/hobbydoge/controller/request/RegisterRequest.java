package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class RegisterRequest {

  @Size(min = 3, message = "Name should not be less than 3 characters.")
  @NotNull(message = "Name cannot be empty.")
  private String name;

  @Size(min = 2, message = "Surname should not be less than 3 characters.")
  @NotNull(message = "Surname cannot be empty.")
  private String surname;

  @Size(min = 3, message = "Username should not be less than 3 characters.")
  @NotNull(message = "Username cannot be empty.")
  private String username;

  @Email(message = "Email must be valid.")
  @NotNull(message = "Email cannot be empty.")
  private String email;

  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must be at least 6 characters long, contain at least 1 letter and 1 number.")
  @NotNull(message = "Password cannot be empty.")
  private String password;

  @NotNull(message = "Date of birth cannot be empty.")
  private String dateOfBirth;

  private String profilePicture;

}
