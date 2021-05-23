package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.SubClubDTO;
import org.koroglu.hobbydoge.model.SubClub;

public class SubClubMapper {
  public static SubClubDTO toSubClubDTO(SubClub subClub) {
    return new SubClubDTO()
            .setId(subClub.getId())
            .setName(subClub.getName())
            .setDescription(subClub.getName())
            .setPicture(subClub.getPicture())
            .setRating(subClub.getRating())
            .setMembers(subClub.getMembers());
            //.setSubClubs(club.getSubClubs());

  }

}
