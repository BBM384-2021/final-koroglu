package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.ReviewRequest;
import org.koroglu.hobbydoge.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/reviews")
@AllArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping("/club/{clubId}/add")
  public ResponseEntity<?> addClubReview(@PathVariable("clubId") Long clubId,
                                         @RequestBody @Valid ReviewRequest reviewRequest) {

    return ResponseEntity.ok().body(reviewService.addClubReview(clubId, reviewRequest));
  }

  @PostMapping("/subclub/{subclubId}/add")
  public ResponseEntity<?> addSubClubReview(@PathVariable("subclubId") Long subClubId,
                                            @RequestBody @Valid ReviewRequest reviewRequest) {

    return ResponseEntity.ok().body(reviewService.addSubClubReview(subClubId, reviewRequest));
  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<?> deleteReview(@PathVariable("reviewId") Long reviewId) {

    return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
  }

}
