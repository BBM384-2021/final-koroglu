package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestClubDoesNotExistException extends BaseException{
  public RestClubDoesNotExistException() {
    super(BaseExceptionType.REST_CLUB_DOES_NOT_EXIST_EXCEPTION, null, null);
  }

  @Builder
  public RestClubDoesNotExistException(String message, List<String> errors) {
    super(BaseExceptionType.REST_CLUB_DOES_NOT_EXIST_EXCEPTION, message, errors);
  }
}
