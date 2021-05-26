package org.koroglu.hobbydoge.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.koroglu.hobbydoge.model.Club;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class SurveyQuestionDTO {

  private Long id;
  private String question;
  private boolean isIncrease;
  private Long clubId;
}
