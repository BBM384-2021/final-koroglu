package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class NewClubRequest {
  @Size(min = 3, message = "Name should not be less than 3 characters.")
  @NotNull(message = "Name cannot be empty.")
  private String name;

  @Size(min = 15, message = "Description should not be less than 15 characters.")
  @NotNull(message = "Description cannot be empty.")
  private String description;

  private String picture;
}
