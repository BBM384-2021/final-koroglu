package org.koroglu.hobbydoge.dto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.koroglu.hobbydoge.model.SubClub;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class ClubDTO {

  private Long id;
  private String name;
  private String description;
  private String picture;
  private Double rating;
  private Set<ClubUserDTO> members;
  private Set<SubClub> subClubs;
  private List<ReviewDTO> reviews;
}
