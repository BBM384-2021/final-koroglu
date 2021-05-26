package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.Club;
import org.koroglu.hobbydoge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByUsername(String username);

  Boolean existsByEmail(String email);

  @Transactional
  @Query(value = "SELECT * FROM users u ORDER BY u.id offset ?1 limit ?2", nativeQuery = true)
  List<User> getAllUsers(int offset, int limit);

  @Transactional
  @Modifying
  @Query("UPDATE users u " + "SET u.isConfirmed = TRUE WHERE u.id = ?1")
  int enableUser(Long id);

  @Transactional
  @Modifying
  @Query("UPDATE users u SET u.password = ?2 WHERE u.id = ?1")
  int updatePassword(Long id, String encryptedPassword);



}
