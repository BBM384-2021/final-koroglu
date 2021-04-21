package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestGeneralException extends BaseException {

  public RestGeneralException() {
    super(BaseExceptionType.GENERAL_EXCEPTION, null, null);
  }

  @Builder
  private RestGeneralException(String message, List<String> errors) {
    super(BaseExceptionType.GENERAL_EXCEPTION, message, errors);
  }
}