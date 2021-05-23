package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.ClubUserDTO;
import org.koroglu.hobbydoge.model.User;

public class ClubUserMapper {
  public static ClubUserDTO toClubUserDTO(User user) {
    return new ClubUserDTO()
            .setName(user.getName())
            .setSurname(user.getSurname())
            .setUsername(user.getUsername())
            .setProfilePicture(user.getProfilePicture());

  }
}


