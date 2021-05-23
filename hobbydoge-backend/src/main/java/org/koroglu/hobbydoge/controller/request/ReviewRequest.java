package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ReviewRequest {

  @NotNull(message = "Comment cannot be empty.")
  private String comment;

  @NotNull(message = "Review cannot be empty.")
  private Double rating;
}
