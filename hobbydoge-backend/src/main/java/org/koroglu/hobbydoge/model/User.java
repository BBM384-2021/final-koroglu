package org.koroglu.hobbydoge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "users")
@JsonIgnoreProperties(value = {"password", "roles", "authorities"})
public class User {

  @EqualsAndHashCode.Include
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
  @JsonIgnore
  private String password;
  private Boolean isBanned = false;
  private Boolean isAnswered = false;
  private Boolean isAdmin = false;
  private Boolean isConfirmed = false;

  @ManyToMany
  Set<Club> enrolledClubs;

  @ManyToMany(fetch = FetchType.EAGER)
  Set<SubClub> enrolledSubClubs;

  @JsonIgnore
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

//  @Override
//  public int hashCode() {
//    return this.id.hashCode();
//  }
//
//  @Override
//  public boolean equals(Object obj) {
//    if (!(obj instanceof User)) {
//      return false;
//    }
//    User other = (User) obj;
//    return this.id.equals(other.getId());
//  }
}
