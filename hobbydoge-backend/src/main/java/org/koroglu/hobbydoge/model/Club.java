package org.koroglu.hobbydoge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity(name = "clubs")
public class Club {

  @EqualsAndHashCode.Include
  @SequenceGenerator(
          name = "club_sequence",
          sequenceName = "club_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "club_sequence"
  )
  @Id
  private Long id;

  private String name;
  private String description;
  private String picture;
  private Double rating = 5.0;

  @ManyToMany
  private Set<User> members;

  @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinTable(name = "club_subclubs", joinColumns = @JoinColumn(name = "club_id"), inverseJoinColumns = @JoinColumn(name = "subclub_id"))
  private Set<SubClub> subClubs;

  public Club(String name, String description, String picture) {
    this.name = name;
    this.description = description;
    this.picture = picture;
    this.rating = 5.0;
    this.members = new HashSet<User>();
    this.subClubs = new HashSet<SubClub>();
  }
}
