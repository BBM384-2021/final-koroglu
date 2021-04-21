package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestWrongPasswordException extends BaseException {
  public RestWrongPasswordException() {
    super(BaseExceptionType.REST_WRONG_PASSWORD_EXCEPTION, null, null);
  }

  @Builder
  public RestWrongPasswordException(String message, List<String> errors) {
    super(BaseExceptionType.REST_WRONG_PASSWORD_EXCEPTION, message, errors);
  }
}
