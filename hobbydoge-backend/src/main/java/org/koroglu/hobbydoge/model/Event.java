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
@JsonIgnoreProperties({"subClub"})
@Entity(name = "events")
public class Event {

  @EqualsAndHashCode.Include
  @SequenceGenerator(
          name = "event_sequence",
          sequenceName = "event_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "event_sequence"
  )
  @Id
  private Long id;
  private String title;
  private String description;
  private LocalDateTime time;
  private Boolean isOnline;
  private String address;

  @ManyToMany
  private Set<User> joinedUsers;

  @ManyToOne(fetch = FetchType.LAZY)
  private SubClub subClub;


  public Event(String title, String description, LocalDateTime time, Boolean isOnline, String address, SubClub subClub) {
    this.title = title;
    this.description = description;
    this.time = time;
    this.isOnline = isOnline;
    this.address = address;
    this.subClub = subClub;
    this.joinedUsers = new HashSet<>();
  }
}
