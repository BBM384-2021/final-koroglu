package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestSurveyPointDoesNotExistException extends BaseException {
  public RestSurveyPointDoesNotExistException() {
    super(BaseExceptionType.REST_SURVEY_POINT_DOES_NOT_EXIST_EXCEPTION, null, null);
  }

  @Builder
  public RestSurveyPointDoesNotExistException(String message, List<String> errors) {
    super(BaseExceptionType.REST_SURVEY_POINT_DOES_NOT_EXIST_EXCEPTION, message, errors);
  }
}
