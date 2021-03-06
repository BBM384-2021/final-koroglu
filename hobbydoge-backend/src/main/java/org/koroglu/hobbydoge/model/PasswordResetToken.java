package org.koroglu.hobbydoge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity(name = "password_reset_tokens")
public class PasswordResetToken {

  @EqualsAndHashCode.Include
  @SequenceGenerator(
          name = "password_reset_token_sequence",
          sequenceName = "password_reset_token_sequence",
          allocationSize = 1
  )
  @Id
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "password_reset_token_sequence"
  )
  private Long id;

  @Column(nullable = false)
  private String token;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime expiresAt;

  private LocalDateTime resetedAt;

  @OneToOne
  private User user;

  public PasswordResetToken(
          LocalDateTime createdAt,
          LocalDateTime expiresAt,
          User user) {

    Random rand = new Random();
    int number = rand.nextInt(999999);
    this.token = String.format("%06d", number);
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
    this.user = user;
  }
}