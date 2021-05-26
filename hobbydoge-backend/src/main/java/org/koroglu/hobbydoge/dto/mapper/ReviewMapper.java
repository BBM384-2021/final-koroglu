package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.ReviewDTO;
import org.koroglu.hobbydoge.model.Review;

public class ReviewMapper {
  public static ReviewDTO toReviewDTO(Review review) {
    return new ReviewDTO()
            .setId(review.getId())
            .setComment(review.getComment())
            .setRating(review.getRating())
            .setUsername(review.getUser().getUsername());

  }

}
