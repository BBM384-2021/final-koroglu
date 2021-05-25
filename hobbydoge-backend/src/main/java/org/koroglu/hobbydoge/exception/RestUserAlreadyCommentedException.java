package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestUserAlreadyCommentedException extends BaseException{
  public RestUserAlreadyCommentedException() {
    super(BaseExceptionType.REST_USER_ALREADY_COMMENTED, null, null);
  }

  @Builder
  private RestUserAlreadyCommentedException(String message, List<String> errors) {
    super(BaseExceptionType.REST_USER_ALREADY_COMMENTED, message, errors);
  }
}
