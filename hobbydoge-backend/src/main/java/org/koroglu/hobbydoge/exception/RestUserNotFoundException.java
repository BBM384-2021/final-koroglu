package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestUserNotFoundException extends BaseException {

  public RestUserNotFoundException() {
    super(BaseExceptionType.REST_USER_NOT_FOUND_EXCEPTION, null, null);
  }

  @Builder
  public RestUserNotFoundException(String message, List<String> errors) {
    super(BaseExceptionType.REST_USER_NOT_FOUND_EXCEPTION, message, errors);
  }

}
