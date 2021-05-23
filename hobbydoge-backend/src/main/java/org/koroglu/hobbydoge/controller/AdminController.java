package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
public class AdminController {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<?> getUsers(@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                    @RequestParam(value = "offset", required = false, defaultValue = "0") int offset) {
    return ResponseEntity.ok().body(userService.getUsers(offset, limit));
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
    return ResponseEntity.ok().body(userService.getUser(userId));
  }

}
