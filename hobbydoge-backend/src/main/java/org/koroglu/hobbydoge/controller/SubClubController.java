package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.NewEventRequest;
import org.koroglu.hobbydoge.controller.request.NewSubClubRequest;
import org.koroglu.hobbydoge.controller.request.SubClubRequest;
import org.koroglu.hobbydoge.service.EventService;
import org.koroglu.hobbydoge.service.SubClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/subclubs")
@AllArgsConstructor
public class SubClubController {

  private final SubClubService subClubService;
  private final EventService eventService;

  @GetMapping("/{subClubId}")
  public ResponseEntity<?> getSubClub(@PathVariable("subClubId") Long subClubId) {
    return ResponseEntity.ok().body(subClubService.get(subClubId));
  }

  @PostMapping("")
  public ResponseEntity<?> createSubClub(@RequestBody @Valid NewSubClubRequest newSubClubRequest) {
    return ResponseEntity.ok().body(subClubService.create(newSubClubRequest));
  }

  @PutMapping("/{subClubId}")
  public ResponseEntity<?> updateSubClub(@PathVariable("subClubId") Long subClubId, @RequestBody @Valid SubClubRequest subClubRequest) {
    return ResponseEntity.ok().body(subClubService.update(subClubId, subClubRequest));
  }

  @DeleteMapping("/{subClubId}")
  public ResponseEntity<?> deleteSubClub(@PathVariable("subClubId") Long subClubId) {
    return ResponseEntity.ok().body(subClubService.delete(subClubId));
  }

  @PostMapping("/{subClubId}/join")
  public ResponseEntity<?> joinSubClub(@PathVariable("subClubId") Long subClubId) {
    return ResponseEntity.ok().body(subClubService.join(subClubId));
  }

  @DeleteMapping("/{subClubId}/leave")
  public ResponseEntity<?> leaveSubClub(@PathVariable("subClubId") Long subClubId) {
    return ResponseEntity.ok().body(subClubService.leave(subClubId));
  }

}
