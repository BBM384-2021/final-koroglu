package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.ClubDTO;
import org.koroglu.hobbydoge.model.Club;

import java.util.stream.Collectors;

public class ClubMapper {
  public static ClubDTO toClubDTO(Club club) {
    return new ClubDTO()
            .setId(club.getId())
            .setName(club.getName())
            .setDescription(club.getDescription())
            .setPicture(club.getPicture())
            .setRating(club.getRating())
            .setMembers(club.getMembers()
                    .stream().map(ClubUserMapper::toClubUserDTO)
                    .collect(Collectors.toSet()))
            .setSubClubs(club.getSubClubs()
                    .stream().map(ListSubClubsMapper::toListSubClubsDTO)
                    .collect(Collectors.toList()));

  }

}
