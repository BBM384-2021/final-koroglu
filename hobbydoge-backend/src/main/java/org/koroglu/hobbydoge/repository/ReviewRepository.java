package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  @Query(value = "SELECT * FROM reviews r WHERE r.is_club = true AND r.club_id = ?1", nativeQuery = true)
  List<Review> getClubReviews(Long clubId);

  @Query(value = "SELECT * FROM reviews r WHERE r.is_club = false AND r.sub_club_id = ?1", nativeQuery = true)
  List<Review> getSubClubReviews(Long subClubId);
}
