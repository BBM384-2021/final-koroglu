package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class NewEventRequest {

  @NotNull(message = "SubClub ID cannot be empty.")
  private Long subClubId;
  @NotNull(message = "Title cannot be empty.")
  private String title;
  @NotNull(message = "Description cannot be empty.")
  private String description;
  @NotNull(message = "Time cannot be empty.")
  private String time;
  @NotNull(message = "Is Online field cannot be empty.")
  private Boolean isOnline;
  @NotNull(message = "Address cannot be empty.")
  private String address;
}
