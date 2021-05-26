package org.koroglu.hobbydoge.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.koroglu.hobbydoge.model.RequestType;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class RequestDTO {
  private Long id;
  private String username;
  private String body;
  private RequestType type;
}
