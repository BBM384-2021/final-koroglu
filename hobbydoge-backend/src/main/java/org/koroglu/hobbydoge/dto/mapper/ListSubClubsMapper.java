package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.ListSubClubsDTO;
import org.koroglu.hobbydoge.model.SubClub;

public class ListSubClubsMapper {
  public static ListSubClubsDTO toListSubClubsDTO(SubClub subClub) {
    return new ListSubClubsDTO()
            .setId(subClub.getId())
            .setName(subClub.getName())
            .setPicture(subClub.getPicture());
  }
}
