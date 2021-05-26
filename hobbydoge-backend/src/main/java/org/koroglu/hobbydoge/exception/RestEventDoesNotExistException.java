package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestEventDoesNotExistException extends BaseException {

  public RestEventDoesNotExistException() {
    super(BaseExceptionType.REST_EVENT_DOES_NOT_EXIST, null, null);
  }

  @Builder
  public RestEventDoesNotExistException(String message, List<String> errors) {
    super(BaseExceptionType.REST_EVENT_DOES_NOT_EXIST, message, errors);
  }

}

