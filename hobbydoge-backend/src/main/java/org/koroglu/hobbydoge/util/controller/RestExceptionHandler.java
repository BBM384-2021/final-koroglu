package org.koroglu.hobbydoge.util.controller;

import org.koroglu.hobbydoge.enums.BaseExceptionType;
import org.koroglu.hobbydoge.exception.*;
import org.koroglu.hobbydoge.util.RestAPIError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
    System.out.println(ex);
    ex.printStackTrace();
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .type(BaseExceptionType.GENERAL_EXCEPTION)
            .message(ex.getLocalizedMessage())
            .errors(Arrays.asList(ex.getMessage()))
            .build();
    return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
  }

  @ExceptionHandler({RestUsernameAlreadyExistException.class})
  public ResponseEntity<Object> handleUsernameAlreadyTakenException(RestUsernameAlreadyExistException ex) {
    System.out.println("handleUsernameAlreadyTakenException");
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.NOT_ACCEPTABLE.value())
            .type(ex.getType())
            .message(ex.getType().getMessage())
            .errors(Arrays.asList("Username already exist."))
            .build();
    return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
  }

  @ExceptionHandler({RestEmailAlreadyExistException.class})
  public ResponseEntity<Object> handleEmailAlreadyTakenException(RestEmailAlreadyExistException ex) {
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.NOT_ACCEPTABLE.value())
            .type(ex.getType())
            .message(ex.getType().getMessage())
            .errors(Arrays.asList("Email already exist."))
            .build();
    return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
  }

  @ExceptionHandler({RestUserNotFoundException.class})
  public ResponseEntity<Object> handleRestEmailNotFoundException(RestUserNotFoundException ex) {
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.NOT_FOUND.value())
            .type(ex.getType())
            .message(ex.getType().getMessage())
            .errors(Arrays.asList("User not found."))
            .build();
    return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
  }

  @ExceptionHandler({RestWrongPasswordException.class})
  public ResponseEntity<Object> handleRestWrongPasswordException(RestWrongPasswordException ex) {
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.NOT_ACCEPTABLE.value())
            .type(ex.getType())
            .message(ex.getType().getMessage())
            .errors(Arrays.asList("Wrong password."))
            .build();
    return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
  }

  @ExceptionHandler({RestClubDoesNotExistException.class})
  public ResponseEntity<Object> handleRestClubDoesNotExistException(RestClubDoesNotExistException ex) {
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.NOT_FOUND.value())
            .type(ex.getType())
            .message(ex.getType().getMessage())
            .errors(Arrays.asList("Club does not exist."))
            .build();
    return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
  }


  @ExceptionHandler({RestClubNameAlreadyExistException.class})
  public ResponseEntity<Object> handleRestClubNameAlreadyExistException(RestClubNameAlreadyExistException ex) {
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.NOT_ACCEPTABLE.value())
            .type(ex.getType())
            .message(ex.getType().getMessage())
            .errors(Arrays.asList("A club with this name already exist."))
            .build();
    return ResponseEntity.status(apiError.getHttpStatus()).headers(new HttpHeaders()).body(apiError);
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> errors = new ArrayList<String>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

    System.out.println(errors);
    System.out.println(ex.getLocalizedMessage());

    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.BAD_REQUEST.value())
            .type(BaseExceptionType.REST_METHOD_ARGUEMENT_NOT_VALID_EXCEPTION)
            .message(BaseExceptionType.REST_METHOD_ARGUEMENT_NOT_VALID_EXCEPTION.getMessage())
            .errors(errors)
            .build();

    return ResponseEntity.badRequest().headers(headers).body(apiError);
  }


}
