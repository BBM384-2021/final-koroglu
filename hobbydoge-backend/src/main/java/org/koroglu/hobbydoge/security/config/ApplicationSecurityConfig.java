package org.koroglu.hobbydoge.security.config;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.security.jwt.AuthTokenFilter;
import org.koroglu.hobbydoge.security.jwt.JWTAuthenticationFilter;
import org.koroglu.hobbydoge.security.jwt.JwtUtils;
import org.koroglu.hobbydoge.service.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserServiceImpl userService;
  private final JwtUtils jwtUtils;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
            .cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterAfter(new AuthTokenFilter(userService, jwtUtils), JWTAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/api/v1/user/**").permitAll()
            .anyRequest()
            .authenticated()
    ;
  }

}
