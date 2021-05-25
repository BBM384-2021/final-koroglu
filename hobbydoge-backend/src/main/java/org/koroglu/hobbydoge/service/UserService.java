package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.LoginRequest;
import org.koroglu.hobbydoge.controller.request.RegisterRequest;
import org.koroglu.hobbydoge.controller.request.ResetPasswordRequest;
import org.koroglu.hobbydoge.controller.request.UpdateUserRequest;
import org.koroglu.hobbydoge.dto.model.ListUsersDTO;
import org.koroglu.hobbydoge.dto.model.LoginDTO;
import org.koroglu.hobbydoge.dto.model.UserDTO;
import org.koroglu.hobbydoge.model.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {

  UserDTO getUser(Long id);

  List<ListUsersDTO> getUsers(int offset, int limit);

  UserDTO updateUser(Long userId, UpdateUserRequest updateUserRequest);

  User getByEmail(String email);

  LoginDTO register(RegisterRequest registerRequest) throws ParseException;

  LoginDTO login(LoginRequest loginRequest);

  String confirmUser(String token);

  String resetPassword(ResetPasswordRequest resetPasswordRequest);

}
