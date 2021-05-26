package org.koroglu.hobbydoge.exception;

import lombok.Builder;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

public class RestUserDontHavePermissionException extends BaseException{
  public RestUserDontHavePermissionException() {
    super(BaseExceptionType.REST_USER_DONT_HAVE_PERMISSION, null, null);
  }

  @Builder
  private RestUserDontHavePermissionException(String message, List<String> errors) {
    super(BaseExceptionType.REST_USER_DONT_HAVE_PERMISSION, message, errors);
  }
}
