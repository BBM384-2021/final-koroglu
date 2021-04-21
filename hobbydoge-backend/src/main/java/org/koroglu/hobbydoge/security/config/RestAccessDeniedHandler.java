package org.koroglu.hobbydoge.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.koroglu.hobbydoge.enums.BaseExceptionType;
import org.koroglu.hobbydoge.util.RestAPIError;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
    System.out.println("RestAccessDeniedHandler");
    System.out.println(e.getMessage());
    System.out.println(e.getLocalizedMessage());
    RestAPIError apiError = RestAPIError.builder()
            .httpStatus(HttpStatus.METHOD_NOT_ALLOWED.value())
            .type(BaseExceptionType.REST_ACCESS_DENIED_EXCEPTION)
            .message(BaseExceptionType.REST_ACCESS_DENIED_EXCEPTION.getMessage())
            .errors(Arrays.asList("You don't have permission."))
            .build();

    OutputStream out = httpServletResponse.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, apiError);
    out.flush();
  }
}
