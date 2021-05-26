package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestSurveyQuestionDoesNotExistException extends BaseException{
  public RestSurveyQuestionDoesNotExistException() {
    super(BaseExceptionType.REST_SURVEY_QUESTION_DOES_NOT_EXIST_EXCEPTION, null, null);
  }

  @Builder
  public RestSurveyQuestionDoesNotExistException(String message, List<String> errors) {
    super(BaseExceptionType.REST_SURVEY_QUESTION_DOES_NOT_EXIST_EXCEPTION, message, errors);
  }
}
