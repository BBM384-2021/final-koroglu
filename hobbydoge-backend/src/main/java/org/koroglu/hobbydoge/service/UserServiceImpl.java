package org.koroglu.hobbydoge.service;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.controller.request.LoginRequest;
import org.koroglu.hobbydoge.controller.request.RegisterRequest;
import org.koroglu.hobbydoge.controller.request.ResetPasswordRequest;
import org.koroglu.hobbydoge.controller.request.UpdateUserRequest;
import org.koroglu.hobbydoge.dto.mapper.ListUsersMapper;
import org.koroglu.hobbydoge.dto.mapper.LoginMapper;
import org.koroglu.hobbydoge.dto.mapper.UserMapper;
import org.koroglu.hobbydoge.dto.model.ListUsersDTO;
import org.koroglu.hobbydoge.dto.model.LoginDTO;
import org.koroglu.hobbydoge.dto.model.UserDTO;
import org.koroglu.hobbydoge.exception.*;
import org.koroglu.hobbydoge.model.*;
import org.koroglu.hobbydoge.repository.ConfirmationTokenRepository;
import org.koroglu.hobbydoge.repository.PasswordResetTokenRepository;
import org.koroglu.hobbydoge.repository.RoleRepository;
import org.koroglu.hobbydoge.repository.UserRepository;
import org.koroglu.hobbydoge.security.jwt.JwtUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final ConfirmationTokenRepository confirmationTokenRepository;
  private final PasswordResetTokenRepository passwordResetTokenRepository;
  private final EmailService emailService;

  BCryptPasswordEncoder bCryptPasswordEncoder;
  JwtUtils jwtUtils;

  @Override
  public UserDTO getUser(Long id) {
    return UserMapper.toUserDto(userRepository.findById(id).orElseThrow(RestUserNotFoundException::new));
  }

  @Override
  public List<ListUsersDTO> getUsers(int offset, int limit) {
    return userRepository.getAllUsers(offset, limit).stream()
            .map(ListUsersMapper::toListUsersDTO).collect(Collectors.toList());
  }

  @Override
  public UserDTO updateUser(Long userId, UpdateUserRequest updateUserRequest) {

    User user = userRepository.findById(userId).orElseThrow(RestUserNotFoundException::new);

    if (updateUserRequest.getName() != null) {
      user.setName(updateUserRequest.getName());
    }

    if (updateUserRequest.getSurname() != null) {
      user.setSurname(updateUserRequest.getSurname());
    }

    if (updateUserRequest.getUsername() != null) {
      Optional<User> existingUsername = userRepository.findByUsername(updateUserRequest.getUsername());

      if (existingUsername.isPresent()) throw new RestUsernameAlreadyExistException();

      user.setUsername(updateUserRequest.getUsername());
    }

    if (updateUserRequest.getEmail() != null) {
      Optional<User> existingUsername = userRepository.findByEmail(updateUserRequest.getEmail());

      if (existingUsername.isPresent()) throw new RestEmailAlreadyExistException();

      user.setEmail(updateUserRequest.getEmail());
    }

    if (updateUserRequest.getDateOfBirth() != null) {
      user.setDateOfBirth(
              LocalDate.parse(
                      updateUserRequest.getDateOfBirth(),
                      DateTimeFormatter.ofPattern("dd/MM/yyyy"))
      );
    }

    if (updateUserRequest.getProfilePicture() != null) {
      user.setProfilePicture(updateUserRequest.getProfilePicture());
    }

    userRepository.save(user);

    return UserMapper.toUserDto(user);

  }

  public User getByEmail(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found."));
  }

  @Override
  public LoginDTO register(RegisterRequest registerRequest) throws ParseException {

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

    ConfirmationToken token = new ConfirmationToken(
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(30),
            newUser
    );

    confirmationTokenRepository.save(token);

    emailService.send(
            newUser.getEmail(),
            "Welcome to HobbyDoge! Confirm your account.",
            "Confirm your account",
            "To complete and secure your HobbyDoge account, we need to confirm your account.",
            token.getToken());

    String jwtToken = Jwts.builder().setSubject(newUser.getEmail())
            .claim("authorities", newUser.getAuthorities())
            .setIssuedAt(new Date())
            .signWith(jwtUtils.secretKey()).compact();

    return LoginMapper.toLoginDTO(newUser, jwtToken);

//    return String.format("Registered successfully, cToken: %s", token.getToken());
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

  @Override
  public String confirmUser(String token) {
    ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token).orElseThrow(RestTokenDoesNotExist::new);

    User requestUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    if (!confirmationToken.getUser().getId().equals(requestUser.getId())) {
      throw new RestTokenNotValidException();
    }

    if (confirmationToken.getConfirmedAt() != null) {
      throw new RestUserAlreadyConfirmedException();
    }

    LocalDateTime expiresAt = confirmationToken.getExpiresAt();

    if (expiresAt.isBefore(LocalDateTime.now())) {
      throw new RestTokenExpiredException();
    }

    confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());

    enableUser(confirmationToken.getUser().getId());

    return "User confirmed.";

  }

  @Override
  public String resetPassword(ResetPasswordRequest resetPasswordRequest) {
    PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(resetPasswordRequest.getToken()).orElseThrow(RestTokenDoesNotExist::new);

    User user = userRepository.findByEmail(resetPasswordRequest.getEmail()).orElseThrow(RestUserNotFoundException::new);

    if (user.getId() != resetToken.getUser().getId()) {
      throw new RestTokenNotValidException();
    }

    if (resetToken.getResetedAt() != null) {
      throw new RestTokenNotValidException();
    }

    LocalDateTime expiresAt = resetToken.getExpiresAt();

    if (expiresAt.isBefore(LocalDateTime.now())) {
      throw new RestTokenExpiredException();
    }

    String encryptedPassword = bCryptPasswordEncoder.encode(resetPasswordRequest.getPassword());

    userRepository.updatePassword(user.getId(), encryptedPassword);

    passwordResetTokenRepository.updateResetedAt(resetPasswordRequest.getToken(), LocalDateTime.now());

    return "Password changed.";
  }

  public int enableUser(Long id) {
    return userRepository.enableUser(id);
  }

}
