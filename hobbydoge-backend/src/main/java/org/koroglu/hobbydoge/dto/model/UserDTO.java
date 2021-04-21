package org.koroglu.hobbydoge.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class UserDTO {

  private Long id;
  private String name;
  private String surname;
  private String username;
  private String email;
  private LocalDate dateOfBirth;
  private String profilePicture;
  private Boolean isBanned;
  private Boolean isAnswered;
  private Boolean isAdmin;
  private Boolean isVerified;

  public String getFullName() {
    return name != null ? name.concat(" ").concat(surname) : "";
  }

}
