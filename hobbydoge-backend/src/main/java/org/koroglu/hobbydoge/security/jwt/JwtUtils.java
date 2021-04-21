package org.koroglu.hobbydoge.security.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Getter
@Setter
@Configuration
@ConfigurationProperties("application.jwt")
public class JwtUtils {

  private String secretKey = "koroglumercimekcorbasiustuneacisos";
  private String tokenPrefix = "Bearer ";
  private Integer tokenExpirationAfterDays = 30;

  @Bean
  public SecretKey secretKey() {
    return Keys.hmacShaKeyFor(getSecretKey().getBytes());
  }

}
