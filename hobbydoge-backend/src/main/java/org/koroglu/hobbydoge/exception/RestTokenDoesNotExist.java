package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestTokenDoesNotExist extends BaseException{

  public RestTokenDoesNotExist() {
    super(BaseExceptionType.REST_CONFIRMATION_TOKEN_DOES_NOT_EXIST, null, null);
  }

  @Builder
  public RestTokenDoesNotExist(String message, List<String> errors) {
    super(BaseExceptionType.REST_CONFIRMATION_TOKEN_DOES_NOT_EXIST, message, errors);
  }
}
