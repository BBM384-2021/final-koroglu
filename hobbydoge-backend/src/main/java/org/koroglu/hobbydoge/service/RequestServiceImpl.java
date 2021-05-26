package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.NewRequest;
import org.koroglu.hobbydoge.dto.mapper.RequestMapper;
import org.koroglu.hobbydoge.dto.model.RequestDTO;
import org.koroglu.hobbydoge.exception.RestEventDoesNotExistException;
import org.koroglu.hobbydoge.model.Request;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.RequestRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

  private final RequestRepository requestRepository;

  @Override
  public String createRequest(NewRequest newRequest) {

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Request request = new Request(
            user,
            newRequest.getBody(),
            newRequest.getType()
    );

    requestRepository.save(request);

    return "Request saved.";
  }

  @Override
  public List<RequestDTO> getRequests() {
    return requestRepository.findAll()
            .stream().map(RequestMapper::toRequestDTO)
            .collect(Collectors.toList());
  }

  @Override
  public String deleteRequest(Long requestId) {

    //TODO: Add Exception
    Request request = requestRepository.findById(requestId).orElseThrow(RestEventDoesNotExistException::new);

    requestRepository.delete(request);

    return "Request deleted successfully";
  }
}
