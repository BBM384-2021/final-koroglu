package org.koroglu.hobbydoge.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    try {
      AuthenticationRequest authenticationRequest = new ObjectMapper().readValue(request.getInputStream(), AuthenticationRequest.class);
      Authentication authentication = new UsernamePasswordAuthenticationToken(
              authenticationRequest.getEmail(),
              authenticationRequest.getPassword()
      );

//      if (authenticationRequest.getEmail() == null || authenticationRequest.getPassword() == null) {
//        response.getOutputStream().println("bi hata oldu amk");
//        throw new IOException("bi hata oldu amk");
//      }

      Authentication authenticate = authenticationManager.authenticate(authentication);
      return authenticate;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

    String token = Jwts.builder().setSubject(authResult.getName())
            .claim("authorities", authResult.getAuthorities())
            .setIssuedAt(new Date())
            .signWith(jwtUtils.secretKey()).compact();


    response.addHeader(HttpHeaders.AUTHORIZATION, jwtUtils.getTokenPrefix() + token);

  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

    System.out.println("UnsuccessfulAuthentication");
    System.out.println(failed.getMessage());
    System.out.println("--------------");
    super.unsuccessfulAuthentication(request, response, failed);
  }
}
