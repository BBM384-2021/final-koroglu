package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.ListUsersDTO;
import org.koroglu.hobbydoge.model.User;

public class ListUsersMapper {
  public static ListUsersDTO toListUsersDTO(User user) {
    return new ListUsersDTO()
            .setId(user.getId())
            .setUsername(user.getUsername())
            .setProfilePicture(user.getProfilePicture());
  }

}
