package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestUserAlreadyConfirmedException extends BaseException{
  public RestUserAlreadyConfirmedException() {
    super(BaseExceptionType.REST_USER_ALREADY_CONFIRMED, null, null);
  }

  @Builder
  private RestUserAlreadyConfirmedException(String message, List<String> errors) {
    super(BaseExceptionType.REST_USER_ALREADY_CONFIRMED, message, errors);
  }
}
