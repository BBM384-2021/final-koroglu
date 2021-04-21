package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestEmailAlreadyExistException extends BaseException {

  public RestEmailAlreadyExistException() {
    super(BaseExceptionType.EMAIL_ALREADY_EXIST_EXCEPTION, null, null);
  }

  @Builder
  public RestEmailAlreadyExistException(String message, List<String> errors) {
    super(BaseExceptionType.EMAIL_ALREADY_EXIST_EXCEPTION, message, errors);
  }
}
