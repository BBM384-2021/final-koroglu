package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.SurveyPointDTO;
import org.koroglu.hobbydoge.dto.model.SurveyQuestionDTO;
import org.koroglu.hobbydoge.model.SurveyPoint;
import org.koroglu.hobbydoge.model.SurveyQuestion;

import java.util.UUID;

public class SurveyPointMapper {
  public static SurveyPointDTO toSurveyPointDTO(SurveyPoint surveyPoint) {
    return new SurveyPointDTO()
            .setId(surveyPoint.getId())
            .setClubId(surveyPoint.getClub().getId())
            .setPoint(surveyPoint.getPoint())
            .setDate(surveyPoint.getDate());

  }

}
