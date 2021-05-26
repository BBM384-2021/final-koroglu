package org.koroglu.hobbydoge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@JsonIgnoreProperties({"club"})
@Entity(name = "subclubs")
public class SubClub {

  @EqualsAndHashCode.Include
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

  @OneToMany
  private Set<Event> events;

  @ManyToOne(fetch = FetchType.LAZY)
  private Club club;

  @OneToMany
  private Set<User> adminRequests;

  public SubClub(String name, String description, String picture, Club club) {
    this.name = name;
    this.description = description;
    this.picture = picture;
    this.club = club;
    this.members = new HashSet<>();
    this.events = new HashSet<>();
    this.adminRequests = new HashSet<>();
  }

}
