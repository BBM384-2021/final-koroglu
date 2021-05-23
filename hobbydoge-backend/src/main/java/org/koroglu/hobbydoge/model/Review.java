package org.koroglu.hobbydoge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"club", "subclub"})
@Entity(name = "reviews")
public class Review {

  @EqualsAndHashCode.Include
  @SequenceGenerator(
          name = "review_sequence",
          sequenceName = "review_sequence",
          allocationSize = 1
  )
  @Id
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "review_sequence"
  )
  private Long id;
  private String comment;
  private Double rating;
  private Boolean isClub;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @OneToOne
  private Club club;

  @OneToOne
  private SubClub subClub;

  @OneToOne
  private User user;

  public Review(String comment, Double rating, Boolean isClub, Club club, SubClub subClub, User user) {
    this.comment = comment;
    this.rating = rating;
    this.isClub = isClub;
    this.createdAt = LocalDateTime.now();
    this.club = club;
    this.subClub = subClub;
    this.user = user;
  }
}
