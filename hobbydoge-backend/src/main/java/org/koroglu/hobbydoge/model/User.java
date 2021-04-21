package org.koroglu.hobbydoge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "users")
public class User {

  @SequenceGenerator(
          name = "user_sequence",
          sequenceName = "user_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "user_sequence"
  )
  @Id
  private Long id;

  private String name;
  private String surname;
  private String username;
  private String email;
  private LocalDate dateOfBirth;
  private String profilePicture;
  private String password;
  private Boolean isBanned = false;
  private Boolean isAnswered = false;
  private Boolean isAdmin = false;
  private Boolean isVerified = false;

  @ManyToMany
  @JoinTable(
          name = "enroll_club",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "club_id"))
  Set<Club> enrolledClubs;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : this.getRoles()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.getName())));
    }
    return grantedAuthorities;
  }

  public User(String name, String surname, String username, String email, String password, LocalDate dateOfBirth, String profilePicture) {
    this.name = name;
    this.surname = surname;
    this.username = username;
    this.email = email;
    this.password = password;
    this.dateOfBirth = dateOfBirth;
    this.profilePicture = profilePicture;
  }

}
