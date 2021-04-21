package org.koroglu.hobbydoge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "subclubs")
public class SubClub {

  @SequenceGenerator(
          name = "subClub_sequence",
          sequenceName = "subClub_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "subClub_sequence"
  )
  @Id
  private Long id;

  private String name;
  private String description;
  private String picture;
  private Double rating;
  private LocalDateTime lastActive;

  @OneToOne
  private User admin;

  @ManyToMany
  private Set<User> members;
}
