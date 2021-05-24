package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.AddSurveyRequest;
import org.koroglu.hobbydoge.controller.request.ClubRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.controller.request.NewSurveyAnswerRequest;
import org.koroglu.hobbydoge.dto.model.ClubDTO;
import org.koroglu.hobbydoge.dto.model.SurveyPointDTO;
import org.koroglu.hobbydoge.dto.model.SurveyQuestionDTO;
import org.koroglu.hobbydoge.model.SurveyQuestion;

import java.util.HashMap;
import java.util.List;

public interface SurveyService {

  List<SurveyQuestionDTO> getSurvey(Long clubId);

  List<SurveyQuestionDTO> createSurvey(AddSurveyRequest addSurveyRequest);

  List<SurveyQuestionDTO> updateSurvey(AddSurveyRequest addSurveyRequest);

  SurveyPointDTO answerSurvey(NewSurveyAnswerRequest newSurveyAnswerRequest);

  SurveyPointDTO getSurveyPoint(Long clubId);
}
