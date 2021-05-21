package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.model.SubClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubClubRepository extends JpaRepository<Club, Long> {

 // List<SubClub> getAllByClub(int offset, int limit); eklenti yapılacak
  Optional<Club> findByName(String name);

}
