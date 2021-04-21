package org.koroglu.hobbydoge.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.model.User;
import org.koroglu.hobbydoge.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

  private final UserService userService;
  private final JwtUtils jwtUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    if ((authorizationHeader == null || authorizationHeader.isEmpty()) || !authorizationHeader.startsWith(jwtUtils.getTokenPrefix())) {
      filterChain.doFilter(request, response);
      return;
    }
    String token = authorizationHeader.replace(jwtUtils.getTokenPrefix(), "");

    try {

      Jws<Claims> claimsJws = Jwts.parserBuilder()
              .setSigningKey(jwtUtils.secretKey())
              .build()
              .parseClaimsJws(token);

      Claims body = claimsJws.getBody();

      String email = body.getSubject();

      System.out.println(email);

      User user = userService.getByEmail(email);

      Authentication authentication = new UsernamePasswordAuthenticationToken(
              user, null, user.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);

    } catch (JwtException e) {
      throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
    }

    filterChain.doFilter(request, response);
  }

}
