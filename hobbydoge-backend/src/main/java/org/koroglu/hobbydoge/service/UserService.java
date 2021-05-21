package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.LoginRequest;
import org.koroglu.hobbydoge.controller.request.RegisterRequest;
import org.koroglu.hobbydoge.controller.request.ResetPasswordRequest;
import org.koroglu.hobbydoge.dto.model.LoginDTO;
import org.koroglu.hobbydoge.model.User;

import java.text.ParseException;

public interface UserService {

  User getByEmail(String email);

  LoginDTO register(RegisterRequest registerRequest) throws ParseException;

  LoginDTO login(LoginRequest loginRequest);

  String confirmUser(String token);

  String resetPassword(ResetPasswordRequest resetPasswordRequest);

}
