package org.koroglu.hobbydoge.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class SurveyPointDTO {

  private Long id;
  private Long clubId;
  private Integer point;
  private Date date;
}
