package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestUsernameAlreadyExistException extends BaseException {

  public RestUsernameAlreadyExistException() {
    super(BaseExceptionType.REST_USER_NOT_VERIFIED_EXCEPTION, null, null);
  }

  @Builder
  public RestUsernameAlreadyExistException(String message, List<String> errors) {
    super(BaseExceptionType.REST_USER_NOT_VERIFIED_EXCEPTION, message, errors);
  }
}
