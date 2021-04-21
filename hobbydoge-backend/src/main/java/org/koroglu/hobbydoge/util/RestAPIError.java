package org.koroglu.hobbydoge.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koroglu.hobbydoge.enums.BaseExceptionType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestAPIError {

  private int httpStatus;
  private BaseExceptionType type;
  private String message;
  private List<String> errors;
  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();
}
