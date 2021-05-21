package org.koroglu.hobbydoge.repository;

import org.koroglu.hobbydoge.model.ConfirmationToken;
import org.koroglu.hobbydoge.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

  Optional<PasswordResetToken> findByToken(String token);

  @Transactional
  @Modifying
  @Query("UPDATE password_reset_tokens pr " + "SET pr.resetedAt = ?2 " + "WHERE pr.token = ?1")
  int updateResetedAt(String token,
                        LocalDateTime confirmedAt);
}
