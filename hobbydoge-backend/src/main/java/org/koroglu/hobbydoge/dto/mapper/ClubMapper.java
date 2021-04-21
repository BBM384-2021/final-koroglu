package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.ClubDTO;
import org.koroglu.hobbydoge.model.Club;

public class ClubMapper {
  public static ClubDTO toClubDTO(Club club) {
    return new ClubDTO()
            .setId(club.getId())
            .setName(club.getName())
            .setDescription(club.getName())
            .setPicture(club.getPicture())
            .setRating(club.getRating())
            .setMembers(club.getMembers())
            .setSubClubs(club.getSubClubs());

  }

}
