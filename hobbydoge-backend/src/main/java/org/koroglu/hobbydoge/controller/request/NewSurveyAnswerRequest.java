package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class NewSurveyAnswerRequest {

  @Min(value = 0,message = "ClubId should not be less than 0.")
  @NotNull(message = "ClubId cannot be null.")
  private Long clubId;


  @NotNull(message = "At least one answer to create point of survey.")
  private List<AnswerRequest> answers;
}
