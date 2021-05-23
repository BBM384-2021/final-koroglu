package org.koroglu.hobbydoge.enums;

import lombok.Getter;

public enum BaseExceptionType {
  GENERAL_EXCEPTION("General exception occured."),
  REST_METHOD_ARGUEMENT_NOT_VALID_EXCEPTION("Arguments passed not valid."),
  EMAIL_ALREADY_EXIST_EXCEPTION("Email already exists."),
  USERNAME_ALREADY_EXIST_EXCEPTION("Username already exists."),
  REST_USER_NOT_VERIFIED_EXCEPTION("User not verified yet."),
  REST_USER_NOT_FOUND_EXCEPTION("User not found."),
  REST_WRONG_PASSWORD_EXCEPTION("Wrong password."),
  REST_ACCESS_DENIED_EXCEPTION("You don't have permission."),
  REST_CLUB_NAME_ALREADY_EXIST_EXCEPTION("A club with this name already exist."),
  REST_CLUB_DOES_NOT_EXIST_EXCEPTION("Club does not exist."),
  REST_CONFIRMATION_TOKEN_DOES_NOT_EXIST("Token does not exist."),
  REST_USER_ALREADY_CONFIRMED("User already confirmed."),
  REST_TOKEN_EXPIRED("Token expired."),
  REST_TOKEN_NOT_VALID("Token is not valid."),
  REST_USER_ALREADY_COMMENTED("User already commented."),
  REST_USER_DONT_HAVE_PERMISSION("You don't have permission.");

  @Getter
  private final String message;

  BaseExceptionType(String message) {
    this.message = message;
  }
}
