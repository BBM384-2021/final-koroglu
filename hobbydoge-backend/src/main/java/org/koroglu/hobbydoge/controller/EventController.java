package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.NewEventRequest;
import org.koroglu.hobbydoge.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/events")
@AllArgsConstructor
public class EventController {

  private final EventService eventService;

  @PostMapping("")
  public ResponseEntity<?> createEvent(@RequestBody @Valid NewEventRequest newEventRequest) {
    return ResponseEntity.ok().body(eventService.createEvent(newEventRequest));
  }

  @DeleteMapping("/{eventId}")
  public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
    System.out.println(eventId);
    return ResponseEntity.ok().body(eventService.deleteEvent(eventId));
  }

  @PostMapping("/{eventId}/join")
  public ResponseEntity<?> joinEvent(@PathVariable Long eventId) {
    return ResponseEntity.ok().body(eventService.joinEvent(eventId));
  }

  @DeleteMapping("/{eventId}/leave")
  public ResponseEntity<?> leaveEvent(@PathVariable Long eventId) {
    return ResponseEntity.ok().body(eventService.leaveEvent(eventId));
  }
}
