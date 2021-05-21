package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

  private UserService userService;

  @PostMapping("/confirm")
  public ResponseEntity<?> confirmUser(@RequestParam(name = "token") String token) {
    return ResponseEntity.ok(userService.confirmUser(token));
  }

}
