package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UpdateUserRequest {

  private String name;
  private String surname;
  private String username;
  private String email;
  private String dateOfBirth;
  private String profilePicture;

}
