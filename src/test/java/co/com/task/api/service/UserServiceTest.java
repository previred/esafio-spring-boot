package co.com.task.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.task.api.domain.User;
import co.com.task.api.dto.UserDTO;
import co.com.task.api.exceptions.ExceptionManager.NotFoundException;
import co.com.task.api.exceptions.ExceptionManager.SaveException;
import co.com.task.api.exceptions.ExceptionManager.UpdateException;
import co.com.task.api.repository.UserRepository;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    UserRepository userRepository;
    @MockBean
    Validator validator;

    UserServiceImpl userService;
    UserDTO userDto;
    User userDomain;
    Optional<User> optionalUser;

    @BeforeEach
    void init() {
        openMocks(this);
	userService = new UserServiceImpl(userRepository, validator);
	userDto = UserDTO.builder()
		.idUser(UUID.randomUUID())
		.email("edwin@hotmail.com")
		.password("1234")
		.build();
	
	userDomain = User.builder()
		.idUser(UUID.randomUUID())
		.password("1234")
		.email("eedwin@hotmail.com")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .build();
	optionalUser = Optional.of(userDomain);
    }

    @Test
    void getByIdTest() {
	when(userRepository.findById(userDomain.getIdUser())).thenReturn(optionalUser);
	var response = userService.getById(userDomain.getIdUser());
	assertThat(response).isNotNull();
    }

    @Test
    void getByIdTestException() {
	UUID generated = UUID.randomUUID();
	Assertions.assertThrows(NotFoundException.class, () -> userService.getById(generated));
    }

    @Test
    void getAllTest() {
	when(userRepository.findAll()).thenReturn(List.of(userDomain));
	var response = userService.getAll();
	assertThat(response).isNotEmpty();
    }

    @Test
    void saveTest() {
	when(userRepository.save(any())).thenReturn(userDomain);
	var response = userService.save(userDto);
	assertThat(response.getIdUser()).isEqualTo(userDomain.getIdUser());
    }

    @Test
    void saveTestException() {
	when(userRepository.getUserByEmail(any())).thenReturn(optionalUser);
	Assertions.assertThrows(SaveException.class, () -> userService.save(userDto));
    }

    @Test
    void updateTest() {
	when(userRepository.findById(any())).thenReturn(optionalUser);
	when(userRepository.save(any())).thenReturn(userDomain);
	var response = userService.update(userDto);
	assertThat(response.getIdUser()).isEqualTo(userDomain.getIdUser());
    }

    @Test
    void updateTestException() {
	when(userRepository.findById(any())).thenReturn(Optional.empty());
	when(userRepository.save(any())).thenReturn(userDomain);
	Assertions.assertThrows(UpdateException.class, () -> userService.update(userDto));
    }

    @Test
    void deleteTest() {
	when(userRepository.findById(any())).thenReturn(optionalUser);
	doNothing().when(userRepository).deleteById(userDto.getIdUser());
	userService.delete(userDto.getIdUser());
	verify(userRepository, times(1)).delete(any());
    }

    @Test
    void deleteTestException() {
	var userId = userDto.getIdUser();
	when(userRepository.findById(any())).thenReturn(Optional.empty());
	Assertions.assertThrows(NotFoundException.class, () -> userService.delete(userId));
    }

}
