package com.ljcoogan.knights.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.ljcoogan.knights.handler.OAuth2LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig {
  @Value("${frontend.url}")
  private String frontendUrl;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http,
      OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler) throws Exception {
    return http.csrf(configurer -> {
      configurer.disable();
      log.info("Disabled CSRF for all requests.");
    }).cors(cors -> {
      cors.configurationSource(corsConfiguration());
      log.info("Configured CORS to allow requests from frontend");
    }).authorizeHttpRequests(auth -> {
      auth.anyRequest().authenticated();
      log.info("Requiring all requests to be authenticated.");
    }).oauth2Login(oauth2 -> {
      oauth2.successHandler(oAuth2LoginSuccessHandler);
    }).build();
  }

  @Bean
  CorsConfigurationSource corsConfiguration() {
    final var corsConfiguration = new CorsConfiguration();

    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.addAllowedMethod("*");
    corsConfiguration.setExposedHeaders(List.of("origin"));

    corsConfiguration.setAllowedOrigins(List.of(frontendUrl));

    final var source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }
}
