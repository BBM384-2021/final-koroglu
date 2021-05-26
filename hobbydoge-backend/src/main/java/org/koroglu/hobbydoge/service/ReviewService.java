package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.ReviewRequest;
import org.koroglu.hobbydoge.dto.model.ReviewDTO;

import java.util.List;

public interface ReviewService {

  List<ReviewDTO> getClubReviews(Long clubId);

  List<ReviewDTO> getSubClubReviews(Long subClubId);

  String addClubReview(Long clubId, ReviewRequest reviewRequest);

  String addSubClubReview(Long subClubId, ReviewRequest reviewRequest);

  String deleteReview(Long reviewId);

}
