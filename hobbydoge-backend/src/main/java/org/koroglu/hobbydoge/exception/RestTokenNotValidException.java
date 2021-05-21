package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestTokenNotValidException extends BaseException {
  public RestTokenNotValidException() {
    super(BaseExceptionType.REST_TOKEN_NOT_VALID, null, null);
  }

  @Builder
  private RestTokenNotValidException(String message, List<String> errors) {
    super(BaseExceptionType.REST_TOKEN_NOT_VALID, message, errors);
  }
}
