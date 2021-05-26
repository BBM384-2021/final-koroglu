package org.koroglu.hobbydoge.service;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.ReviewRequest;
import org.koroglu.hobbydoge.dto.mapper.ReviewMapper;
import org.koroglu.hobbydoge.dto.model.ReviewDTO;
import org.koroglu.hobbydoge.exception.RestClubDoesNotExistException;
import org.koroglu.hobbydoge.exception.RestUserAlreadyCommentedException;
import org.koroglu.hobbydoge.exception.RestUserDontHavePermissionException;
import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.model.Review;
import org.koroglu.hobbydoge.model.SubClub;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.ClubRepository;
import org.koroglu.hobbydoge.repository.ReviewRepository;
import org.koroglu.hobbydoge.repository.SubClubRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final ClubRepository clubRepository;
  private final SubClubRepository subClubRepository;

  @Override
  public List<ReviewDTO> getClubReviews(Long clubId) {
    return reviewRepository.getClubReviews(clubId)
            .stream().map(ReviewMapper::toReviewDTO).collect(Collectors.toList());
  }

  @Override
  public List<ReviewDTO> getSubClubReviews(Long subClubId) {
    return reviewRepository.getSubClubReviews(subClubId)
            .stream().map(ReviewMapper::toReviewDTO).collect(Collectors.toList());
  }

  @Override
  public String addClubReview(Long clubId, ReviewRequest reviewRequest) {

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Club club = clubRepository.findById(clubId).orElseThrow(RestClubDoesNotExistException::new);

    System.out.println(club.getMembers().contains(user));

    if (!club.getMembers().contains(user)) {
      //TODO: Add new exception for here
      throw new RestUserAlreadyCommentedException();
    }

    Review review = new Review(
            reviewRequest.getComment(),
            reviewRequest.getRating(),
            true,
            club,
            null,
            user
    );

    reviewRepository.save(review);

    return "Review saved successfully.";

  }

  @Override
  public String addSubClubReview(Long subClubId, ReviewRequest reviewRequest) {

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    //TODO: Add new exception
    SubClub subClub = subClubRepository.findById(subClubId).orElseThrow(RestClubDoesNotExistException::new);

    Review review = new Review(
            reviewRequest.getComment(),
            reviewRequest.getRating(),
            false,
            null,
            subClub,
            user
    );

    reviewRepository.save(review);

    return "Review saved successfully.";

  }

  @Override
  public String deleteReview(Long reviewId) {

    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    Review review = reviewRepository.findById(reviewId).orElseThrow(RestClubDoesNotExistException::new);

    if (!user.getId().equals(review.getUser().getId()) && !user.getAuthorities().contains("ROLE_ADMIN")) {
      throw new RestUserDontHavePermissionException();
    }

    reviewRepository.delete(review);

    return "Review deleted successfully.";

  }
}
