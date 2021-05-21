package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestTokenExpiredException extends BaseException{
  public RestTokenExpiredException() {
    super(BaseExceptionType.REST_TOKEN_EXPIRED, null, null);
  }

  @Builder
  private RestTokenExpiredException(String message, List<String> errors) {
    super(BaseExceptionType.REST_TOKEN_EXPIRED, message, errors);
  }
}
