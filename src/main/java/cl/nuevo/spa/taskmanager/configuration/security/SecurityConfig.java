package cl.nuevo.spa.taskmanager.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.sessionManagement(
            sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(AbstractHttpConfigurer::disable)
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(
            (auth) -> {
              try {
                auth.antMatchers(
                        "/",
                        "/**/auth/**",
                        "/**/h2-console/**",
                        "/**/h2-console/*.*",
                        "/**/h2_console/**",
                        "/**/h2_console/*.*",
                        "/**/swagger-ui.html",
                        "/**/swagger-ui/**",
                        "/**/swagger-resources/**",
                        "/**/swagger-resources/*.*",
                        "/**/v3/api-docs/**",
                        "/**/proxy/**",
                        "/**/swagger-ui/**",
                        "/**/swagger-ui/*.*",
                        "/**/api-docs/**",
                        "/**/api-docs/*.*")
                    .permitAll()
                    .and()
                    .headers()
                    .frameOptions()
                    .sameOrigin()
                    .and()
                    .authorizeHttpRequests()
                    .anyRequest()
                    .authenticated()
                    .and();
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            })
        .build();
  }
}
