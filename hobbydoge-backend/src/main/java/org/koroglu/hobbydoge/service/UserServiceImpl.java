package org.koroglu.hobbydoge.service;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.LoginRequest;
import org.koroglu.hobbydoge.controller.request.RegisterRequest;
import org.koroglu.hobbydoge.dto.mapper.LoginMapper;
import org.koroglu.hobbydoge.dto.model.LoginDTO;
import org.koroglu.hobbydoge.exception.*;
import org.koroglu.hobbydoge.model.Role;
import org.koroglu.hobbydoge.model.RoleEnum;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.repository.RoleRepository;
import org.koroglu.hobbydoge.repository.UserRepository;
import org.koroglu.hobbydoge.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  @Autowired
  private final UserRepository userRepository;

  @Autowired
  private final RoleRepository roleRepository;

  BCryptPasswordEncoder bCryptPasswordEncoder;
  JwtUtils jwtUtils;


  public User getByEmail(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found."));
  }

  @Override
  public String register(RegisterRequest registerRequest) throws ParseException {

    Optional<User> user = userRepository.findByEmail(registerRequest.getEmail());
    if (user.isPresent()) {
      throw new RestEmailAlreadyExistException();
    }
    user = userRepository.findByUsername(registerRequest.getUsername());
    if (user.isPresent()) {
      throw new RestUsernameAlreadyExistException();
    }

    User newUser = new User(
            registerRequest.getName(),
            registerRequest.getSurname(),
            registerRequest.getUsername(),
            registerRequest.getEmail(),
            bCryptPasswordEncoder.encode(registerRequest.getPassword()),
            LocalDate.parse(registerRequest.getDateOfBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            null
    );

    Set<Role> roles = new HashSet<>();

    Role userRole = roleRepository.findByName(RoleEnum.USER)
            .orElseThrow(() -> new RuntimeException("Role is not found."));
    roles.add(userRole);

    newUser.setRoles(roles);

    userRepository.save(newUser);

    return "Registered successfully";
  }

  @Override
  public LoginDTO login(LoginRequest loginRequest) {

    Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
    if (!optionalUser.isPresent()) {
      throw new RestUserNotFoundException();
    }

    User user = optionalUser.get();

    if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      throw new RestWrongPasswordException();
    }

//    if (!user.getIsVerified()) {
//      throw new RestUserNotVerifiedException();
//    }

    String token = Jwts.builder().setSubject(user.getEmail())
            .claim("authorities", user.getAuthorities())
            .setIssuedAt(new Date())
            .signWith(jwtUtils.secretKey()).compact();

    return LoginMapper.toLoginDTO(user, token);

  }
}
