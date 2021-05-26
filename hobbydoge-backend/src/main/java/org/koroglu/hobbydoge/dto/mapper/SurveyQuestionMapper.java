package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.SurveyQuestionDTO;
import org.koroglu.hobbydoge.model.SurveyQuestion;

public class SurveyQuestionMapper {
  public static SurveyQuestionDTO toSurveyQuestionDTO(SurveyQuestion surveyQuestion) {
    return new SurveyQuestionDTO()
            .setId(surveyQuestion.getId())
            .setQuestion(surveyQuestion.getQuestion())
            .setIncrease(surveyQuestion.isIncrease())
            .setClubId(surveyQuestion.getClub().getId());

  }

}
