package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestClubNameAlreadyExistException extends BaseException {
  public RestClubNameAlreadyExistException() {
    super(BaseExceptionType.REST_CLUB_NAME_ALREADY_EXIST_EXCEPTION, null, null);
  }

  @Builder
  public RestClubNameAlreadyExistException(String message, List<String> errors) {
    super(BaseExceptionType.REST_CLUB_NAME_ALREADY_EXIST_EXCEPTION, message, errors);
  }
}
