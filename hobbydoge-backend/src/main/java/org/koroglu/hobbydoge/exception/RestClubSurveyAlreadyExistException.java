package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestClubSurveyAlreadyExistException extends BaseException {
  public RestClubSurveyAlreadyExistException() {
    super(BaseExceptionType.REST_CLUB_SURVEY_ALREADY_EXIST_EXCEPTION, null, null);
  }

  @Builder
  public RestClubSurveyAlreadyExistException(String message, List<String> errors) {
    super(BaseExceptionType.REST_CLUB_SURVEY_ALREADY_EXIST_EXCEPTION, message, errors);
  }
}
