package cl.nuevo.spa.taskmanager.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import cl.nuevo.spa.taskmanager.domain.exception.UserNotFoundException;
import cl.nuevo.spa.taskmanager.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class UserDetailsAuthenticationServiceTest {

  @Mock private UserRepository userRepository;

  @InjectMocks private UserDetailsAuthenticationService userDetailsService;

  @Test
  void shouldLoadUserByUsername() {
    UserEntity userEntity = UserEntity.builder().build();
    when(userRepository.findByUserName(any())).thenReturn(Optional.of(userEntity));
    UserDetails userDetails = userDetailsService.loadUserByUsername(any());
    Assertions.assertNotNull(userDetails);
    Mockito.verify(userRepository, Mockito.times(1)).findByUserName(any());
  }

  @Test
  void shouldThrowExceptionWhenUserNotFound() {
    when(userRepository.findByUserName(any())).thenReturn(Optional.empty());
    Assertions.assertThrows(
        UserNotFoundException.class, () -> userDetailsService.loadUserByUsername(any()));
    Mockito.verify(userRepository, Mockito.times(1)).findByUserName(any());
  }
}
