package org.koroglu.hobbydoge.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "roles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  private RoleEnum name;

  public Role(RoleEnum name) {
    this.name = name;
  }

}
