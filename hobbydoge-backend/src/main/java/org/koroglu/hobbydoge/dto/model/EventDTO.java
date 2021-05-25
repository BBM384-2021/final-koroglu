package org.koroglu.hobbydoge.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class EventDTO {
  private Long id;
  private String title;
  private String description;
  private LocalDateTime time;
  private Boolean isOnline;
  private String address;
  private int joinedUserCount;
}
