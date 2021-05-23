package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.ListClubsDTO;
import org.koroglu.hobbydoge.model.Club;

public class ListClubsMapper {
  public static ListClubsDTO toListClubsDTO(Club club) {
    return new ListClubsDTO()
            .setId(club.getId())
            .setName(club.getName())
            .setPicture(club.getPicture())
            .setRating(club.getRating());

  }
}
