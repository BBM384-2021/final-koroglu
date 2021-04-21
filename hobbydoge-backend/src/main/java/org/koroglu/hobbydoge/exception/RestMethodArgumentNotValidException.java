package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestMethodArgumentNotValidException extends BaseException {

  public RestMethodArgumentNotValidException() {
    super(BaseExceptionType.REST_METHOD_ARGUEMENT_NOT_VALID_EXCEPTION, null, null);
  }

  @Builder
  private RestMethodArgumentNotValidException(String message, List<String> errors) {
    super(BaseExceptionType.REST_METHOD_ARGUEMENT_NOT_VALID_EXCEPTION, message, errors);
  }

}
