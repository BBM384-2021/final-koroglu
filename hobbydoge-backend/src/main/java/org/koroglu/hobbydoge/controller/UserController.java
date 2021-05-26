package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.UpdateUserRequest;
import org.koroglu.hobbydoge.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

  private final UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<?> getUserDetails(@PathVariable("userId") Long userId) {
    return ResponseEntity.ok("ok");
  }

  @PostMapping("/confirm")
  public ResponseEntity<?> confirmUser(@RequestParam(name = "token") String token) {
    return ResponseEntity.ok(userService.confirmUser(token));
  }

  @PutMapping("/{userId}")
  public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, UpdateUserRequest updateUserRequest) {
    return ResponseEntity.ok().body("aynen knk");
  }

}
