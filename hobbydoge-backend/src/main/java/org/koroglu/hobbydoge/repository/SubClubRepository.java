package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.SubClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubClubRepository extends JpaRepository<SubClub, Long> {
 @Query(value = "SELECT * FROM subclubs sc ORDER BY sc.id offset ?1 limit ?2", nativeQuery = true)

 List<SubClub> getAllSubClubs(int offset, int limit);

  Optional<SubClub> findByName(String name);

 List<SubClub> findByNameContainingIgnoreCase(String name);

}
