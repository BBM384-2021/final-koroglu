package org.koroglu.hobbydoge.controller;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.LoginRequest;
import org.koroglu.hobbydoge.controller.request.RegisterRequest;
import org.koroglu.hobbydoge.controller.request.ResetPasswordRequest;
import org.koroglu.hobbydoge.service.EmailService;
import org.koroglu.hobbydoge.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/auth")
public class AuthController {

  private UserService userService;
  private EmailService emailService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) throws ParseException {
    return ResponseEntity.ok().body(userService.register(registerRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
    return ResponseEntity.ok().body(userService.login(loginRequest));
  }

  @GetMapping("/resetpassword")
  public ResponseEntity<?> resetPassword(@RequestParam(value = "email", required = true) String email) {
    return ResponseEntity.ok().body(emailService.sendResetMail(email));
  }

  @PostMapping("/resetpassword")
  public ResponseEntity<?> resetPassword(@RequestBody @Valid ResetPasswordRequest resetPasswordRequest) {
    return ResponseEntity.ok(userService.resetPassword(resetPasswordRequest));
  }

  @GetMapping("")
  public ResponseEntity<?> getUser(@RequestParam(value = "token") String token) {
    System.out.println(token);
    return ResponseEntity.ok(userService.getUserFromToken(token));
  }

}
