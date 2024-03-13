package cl.previred.challenge.config;

import cl.previred.challenge.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final PasswordEncoder passwordEncoder;
  private final JwtRequestFilter jwtRequestFilter;

  public SecurityConfig(PasswordEncoder passwordEncoder, JwtRequestFilter jwtRequestFilter) {
    this.passwordEncoder = passwordEncoder;
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Bean
  @Order(1)
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(authz -> authz
        .requestMatchers("/auth/login").permitAll()
        .requestMatchers("/actuator/**").permitAll()
        .requestMatchers(HttpMethod.POST).hasAuthority("ROLE_WRITE")
        .requestMatchers(HttpMethod.PUT).hasAuthority("ROLE_WRITE")
        .requestMatchers(HttpMethod.DELETE).hasAuthority("ROLE_WRITE")
        .requestMatchers(HttpMethod.GET).hasAuthority("ROLE_READ")
        .anyRequest().authenticated()
      )
    ;

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
    UserDetailsService userDetailsService
  ) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);

    return new ProviderManager(authenticationProvider);
  }

}
