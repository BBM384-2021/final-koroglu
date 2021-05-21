package org.koroglu.hobbydoge.dto.mapper;

import org.koroglu.hobbydoge.dto.model.LoginDTO;
import org.koroglu.hobbydoge.model.User;

public class LoginMapper {

  public static LoginDTO toLoginDTO(User user, String token) {
    return new LoginDTO()
            .setEmail(user.getEmail())
            .setName(user.getName())
            .setSurname(user.getSurname())
            .setUsername(user.getUsername())
            .setDateOfBirth(user.getDateOfBirth())
            .setProfilePicture(user.getProfilePicture())
            .setIsBanned(user.getIsBanned())
            .setIsAnswered(user.getIsAnswered())
            .setIsAdmin(user.getIsAdmin())
            .setIsConfirmed(user.getIsConfirmed())
            .setToken(token);

  }

}
