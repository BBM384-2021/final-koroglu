package org.koroglu.hobbydoge.controller;

import org.koroglu.hobbydoge.controller.request.LoginRequest;
import org.koroglu.hobbydoge.controller.request.RegisterRequest;
import org.koroglu.hobbydoge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) throws ParseException {
    return ResponseEntity.ok().body(userService.register(registerRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
    return ResponseEntity.ok().body(userService.login(loginRequest));
  }

//  @PostMapping("/verification")
//  public ResponseEntity<?> verificateUser(@RequestBody @Valid VerificationRequest verificationRequest) {
////    return ResponseEntity.ok().body(userService.ver)
//  }

}
