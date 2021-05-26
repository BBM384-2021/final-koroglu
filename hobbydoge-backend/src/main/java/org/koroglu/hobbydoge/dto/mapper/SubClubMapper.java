package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.SubClubDTO;
import org.koroglu.hobbydoge.model.SubClub;

import java.util.stream.Collectors;

public class SubClubMapper {
  public static SubClubDTO toSubClubDTO(SubClub subClub) {
    return new SubClubDTO()
            .setId(subClub.getId())
            .setName(subClub.getName())
            .setDescription(subClub.getDescription())
            .setPicture(subClub.getPicture())
            .setRating(subClub.getRating())
            .setMembers(subClub.getMembers()
                    .stream().map(ClubUserMapper::toClubUserDTO)
                    .collect(Collectors.toSet()))
            .setEvents(subClub.getEvents()
                    .stream().map(EventMapper::toEventDTO)
                    .collect(Collectors.toList()))
            .setAdminRequests(subClub.getAdminRequests()
                    .stream().map(ClubUserMapper::toClubUserDTO)
                    .collect(Collectors.toSet()));
  }

}
