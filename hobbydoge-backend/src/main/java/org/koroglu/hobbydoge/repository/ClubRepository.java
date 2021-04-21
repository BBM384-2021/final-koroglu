package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {

  @Query(value = "SELECT * FROM clubs c ORDER BY c.id offset ?1 limit ?2", nativeQuery = true)
  List<Club> getAllClubs(int offset, int limit);

  Optional<Club> findByName(String name);

}
