package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.UserDTO;
import org.koroglu.hobbydoge.model.User;

public class UserMapper {

  public static UserDTO toUserDto(User user) {
    return new UserDTO()
            .setId(user.getId())
            .setEmail(user.getEmail())
            .setName(user.getName())
            .setSurname(user.getSurname())
            .setUsername(user.getUsername())
            .setDateOfBirth(user.getDateOfBirth())
            .setProfilePicture(user.getProfilePicture())
            .setIsBanned(user.getIsBanned())
            .setIsAnswered(user.getIsAnswered())
            .setIsAdmin(user.getIsAdmin())
            .setIsVerified(user.getIsVerified());
  }

}
