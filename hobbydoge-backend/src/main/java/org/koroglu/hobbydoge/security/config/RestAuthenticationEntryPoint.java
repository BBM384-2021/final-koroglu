package org.koroglu.hobbydoge.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.koroglu.hobbydoge.enums.BaseExceptionType;
import org.koroglu.hobbydoge.util.RestAPIError;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

    System.out.println("RestAuthenticationEntryPoint");
    System.out.println(e.getMessage());
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.NOT_ACCEPTABLE.value())
            .type(BaseExceptionType.USERNAME_ALREADY_EXIST_EXCEPTION)
            .message(BaseExceptionType.USERNAME_ALREADY_EXIST_EXCEPTION.getMessage())
            .errors(Arrays.asList("RestAuthenticationEntryPoint error kismi calisti"))
            .build();

    OutputStream out = response.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, apiError);
    out.flush();
  }
}
