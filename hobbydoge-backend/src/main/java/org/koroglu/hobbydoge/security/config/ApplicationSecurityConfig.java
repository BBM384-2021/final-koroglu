package org.koroglu.hobbydoge.security.config;

import lombok.AllArgsConstructor;
import org.koroglu.hobbydoge.security.jwt.AuthTokenFilter;
import org.koroglu.hobbydoge.security.jwt.JwtUtils;
import org.koroglu.hobbydoge.service.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


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
            .addFilterBefore(new AuthTokenFilter(userService, jwtUtils), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/api/v1/surveyQuestion**", "/api/v1/surveyQuestion/**").permitAll()
            .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**", "/swagger-resources").permitAll()
            .antMatchers("/**").permitAll()
            .antMatchers("/api/v1/auth/**", "/apo/v1/auth**").permitAll()
            .antMatchers("/api/v1/clubs**", "/api/v1/clubs/**", "/api/v1/clubs/**/**", "api/v1/subclubs/**", "api/v1/subclubs**", "api/v1/subclubs/**/**").permitAll()
            .anyRequest()
            .authenticated();
  }

}
