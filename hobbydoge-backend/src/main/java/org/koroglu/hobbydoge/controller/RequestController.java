package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.NewRequest;
import org.koroglu.hobbydoge.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/requests")
@AllArgsConstructor
public class RequestController {

  private final RequestService requestService;

  @PostMapping("/")
  public ResponseEntity<?> createRequest(@RequestBody NewRequest newRequest) {
    return ResponseEntity.ok().body(requestService.createRequest(newRequest));
  }

  @GetMapping("/")
  public ResponseEntity<?> getRequests() {
    return ResponseEntity.ok().body(requestService.getRequests());
  }

  @DeleteMapping("/{requestId}")
  public ResponseEntity<?> deleteRequest(@PathVariable("requestId") Long requestId) {
    return ResponseEntity.ok().body(requestService.deleteRequest(requestId));
  }

}
