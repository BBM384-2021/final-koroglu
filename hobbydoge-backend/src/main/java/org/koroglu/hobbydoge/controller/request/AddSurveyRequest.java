package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AddSurveyRequest {

  @NotNull(message = "ClubId cannot be empty.")
  private Long clubId;

  @NotNull(message = "At least one question to create survey.")
  private List<QuestionRequest> questionRequests;

}



