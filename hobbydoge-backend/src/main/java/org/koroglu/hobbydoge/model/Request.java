package org.koroglu.hobbydoge.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity(name = "requests")
public class Request {

  @EqualsAndHashCode.Include
  @SequenceGenerator(
          name = "request_sequence",
          sequenceName = "request_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "request_sequence"
  )
  @Id
  private Long id;

  @OneToOne
  private User user;

  private String body;

  private RequestType type;

  public Request(User user, String body, RequestType type) {
    this.user = user;
    this.body = body;
    this.type = type;
  }
}
