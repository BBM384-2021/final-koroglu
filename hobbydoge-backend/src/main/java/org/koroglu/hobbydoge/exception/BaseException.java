package org.koroglu.hobbydoge.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"cause", "localizedMessage", "suppressed", "stackTrace"})
public class BaseException extends RuntimeException {

  private BaseExceptionType type;
  private String message;
  private List<String> errors;


}
