package org.koroglu.hobbydoge.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.koroglu.hobbydoge.model.RequestType;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class NewRequest {
  private String body;
  private RequestType type;
}
