package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestUserAlreadyJoinedException extends BaseException {

  public RestUserAlreadyJoinedException() {
    super(BaseExceptionType.REST_ALREADY_JOINED_TO_EVENT, null, null);
  }

  @Builder
  public RestUserAlreadyJoinedException(String message, List<String> errors) {
    super(BaseExceptionType.REST_ALREADY_JOINED_TO_EVENT, message, errors);
  }

}
