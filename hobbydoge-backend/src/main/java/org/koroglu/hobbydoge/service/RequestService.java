package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.NewRequest;
import org.koroglu.hobbydoge.dto.model.RequestDTO;

import java.util.List;

public interface RequestService {

  String createRequest(NewRequest newRequest);

  List<RequestDTO> getRequests();

  String deleteRequest(Long requestId);

}
