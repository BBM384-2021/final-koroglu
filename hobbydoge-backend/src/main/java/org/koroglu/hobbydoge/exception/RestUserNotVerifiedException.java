package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestUserNotVerifiedException extends BaseException {

  public RestUserNotVerifiedException() {
    super(BaseExceptionType.REST_USER_NOT_VERIFIED_EXCEPTION, null, null);
  }

  @Builder
  public RestUserNotVerifiedException(String message, List<String> errors) {
    super(BaseExceptionType.REST_USER_NOT_FOUND_EXCEPTION, message, errors);
  }
}
