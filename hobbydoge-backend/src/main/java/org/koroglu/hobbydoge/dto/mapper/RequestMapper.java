package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.RequestDTO;
import org.koroglu.hobbydoge.model.Request;

public class RequestMapper {
  public static RequestDTO toRequestDTO(Request request) {
    return new RequestDTO()
            .setId(request.getId())
            .setBody(request.getBody())
            .setType(request.getType())
            .setUsername(request.getUser().getUsername());

  }
}
