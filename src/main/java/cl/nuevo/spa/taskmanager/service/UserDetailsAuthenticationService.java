package cl.nuevo.spa.taskmanager.service;

import cl.nuevo.spa.taskmanager.domain.entity.UserAuthDetailsEntity;
import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import cl.nuevo.spa.taskmanager.domain.exception.UserNotFoundException;
import cl.nuevo.spa.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** The type User details authentication service. */
@RequiredArgsConstructor
@Service
public class UserDetailsAuthenticationService implements UserDetailsService {

  /** The constant EXCEPTION_ERROR_CODE. */
  public static final String EXCEPTION_ERROR_CODE = "084041";

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    UserEntity user =
        userRepository
            .findByUserName(userName)
            .orElseThrow(() -> new UserNotFoundException(EXCEPTION_ERROR_CODE));
    return new UserAuthDetailsEntity(user);
  }
}
