package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.ClubRequest;
import org.koroglu.hobbydoge.controller.request.NewClubRequest;
import org.koroglu.hobbydoge.service.ClubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("api/v1/clubs")
@AllArgsConstructor
public class ClubController {

  private final ClubService clubService;

  @GetMapping("/{clubId}")
  public ResponseEntity<?> getClub(@PathVariable("clubId") Long clubId) {
    return ResponseEntity.ok().body(clubService.get(clubId));
  }

  @GetMapping("")
  public ResponseEntity<?> getClubs(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit, @RequestParam(value = "offset", required = false, defaultValue = "0") int offset) {
    System.out.println(String.format("Limit: %d Offset: %d", limit, offset));
    return ResponseEntity.ok().body(clubService.getClubs(offset, limit));
  }

  @PostMapping("")
  public ResponseEntity<?> createClub(@RequestBody @Valid NewClubRequest newClubRequest) {
    return ResponseEntity.ok().body(clubService.create(newClubRequest));
  }

  @PutMapping("/{clubId}")
  public ResponseEntity<?> updateClub(@PathVariable("clubId") Long clubId, @RequestBody @Valid ClubRequest clubRequest) {
    return ResponseEntity.ok().body(clubService.update(clubId, clubRequest));
  }

  @DeleteMapping("/{clubId}")
  public ResponseEntity<?> deleteClub(@PathVariable("clubId") Long clubId) {
    return ResponseEntity.ok().body(clubService.delete(clubId));
  }

  @GetMapping("/search")
  public ResponseEntity<?> searchClubs(@RequestParam(value = "q") @Size(min = 3) String q) {
    return ResponseEntity.ok().body(clubService.search(q));
  }

  @PostMapping("/{clubId}/join")
  public ResponseEntity<?> joinClub(@PathVariable("clubId") Long clubId) {
    return ResponseEntity.ok().body(clubService.join(clubId));
  }

  @DeleteMapping("/{clubId}/leave")
  public ResponseEntity<?> leaveClub(@PathVariable("clubId") Long clubId) {
    return ResponseEntity.ok().body(clubService.leave(clubId));
  }
}
