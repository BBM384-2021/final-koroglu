package org.koroglu.hobbydoge.service;

import org.koroglu.hobbydoge.controller.request.LoginRequest;
import org.koroglu.hobbydoge.controller.request.RegisterRequest;
import org.koroglu.hobbydoge.dto.model.LoginDTO;
import org.koroglu.hobbydoge.model.User;

import java.text.ParseException;

public interface UserService {

  User getByEmail(String email);

  String register(RegisterRequest registerRequest) throws ParseException;

  LoginDTO login(LoginRequest loginRequest);

}
