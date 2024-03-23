package com.reto.tecnico.config;

import com.reto.tecnico.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
  private final JwtRequestFilter jwtRequestFilter;

  public WebSecurityConfig(JwtRequestFilter jwtRequestFilter){
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Bean
  SecurityFilterChain web(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeHttpRequests((authorize) -> authorize
                    .antMatchers("/auth/login").permitAll()
                    .antMatchers("/actuator/health").permitAll()
                    .antMatchers("/swagger-ui/**").permitAll()
                    .antMatchers("/v3/**").permitAll()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement((session) -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
    return http.build();
  }
  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
