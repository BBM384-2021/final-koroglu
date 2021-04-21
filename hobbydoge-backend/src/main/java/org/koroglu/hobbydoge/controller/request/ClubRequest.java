package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ClubRequest {

  @Size(min = 3, message = "Name should not be less than 3 characters.")
  private String name;

  @Size(min = 15, message = "Description should not be less than 15 characters.")
  private String description;

  private String picture;
}
