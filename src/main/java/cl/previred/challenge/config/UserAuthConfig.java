package cl.previred.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserAuthConfig {

  private final PasswordEncoder passwordEncoder;

  public UserAuthConfig(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Bean
  public UserDetailsService userDetailsService() {
    User.UserBuilder users = User.builder();
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(users.username("reader").password(passwordEncoder.encode("1234")).roles("READ").build());
    manager.createUser(users.username("writer").password(passwordEncoder.encode("1234")).roles("READ", "WRITE").build());
    return manager;
  }
}
