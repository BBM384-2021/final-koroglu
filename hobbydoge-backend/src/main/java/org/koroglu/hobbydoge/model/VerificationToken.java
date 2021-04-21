package org.koroglu.hobbydoge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "verification_tokens")
public class VerificationToken {

  @SequenceGenerator(
          name = "verification_token_sequence",
          sequenceName = "verification_token_sequence",
          allocationSize = 1
  )
  @Id
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "verification_token_sequence"
  )
  private Long id;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime expiresAt;

  private LocalDateTime confirmedAt;

  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  public VerificationToken(String token,
                           LocalDateTime createdAt,
                           LocalDateTime expiresAt,
                           User user) {
    this.token = token;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
    this.user = user;
  }
}
