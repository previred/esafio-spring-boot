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

import co.com.task.api.domain.Task;
import co.com.task.api.domain.User;
import co.com.task.api.dto.TaskDTO;
import co.com.task.api.exceptions.ExceptionManager.NotFoundException;
import co.com.task.api.exceptions.ExceptionManager.SaveException;
import co.com.task.api.exceptions.ExceptionManager.UpdateException;
import co.com.task.api.repository.TaskRepository;
import co.com.task.api.repository.UserRepository;

@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    @MockBean
    TaskRepository taskRepository;
    @MockBean
    UserRepository userRepository;
    @MockBean
    Validator validator;

    TaskServiceImpl taskService;
    TaskDTO taskDto;
    Task taskDomain;
    Optional<Task> optionalTask;
    Optional<User> optionalUser;

    @BeforeEach
    void init() {
        openMocks(this);
	taskService = new TaskServiceImpl(taskRepository, userRepository, validator);
	taskDto = TaskDTO.builder()
                .idTask(UUID.randomUUID())
		.description("Task Name")
		.name("Name").email("Email").status("Done")
                .idUser(UUID.randomUUID())
                .build();

	taskDomain = Task.builder()
                .idTask(UUID.randomUUID())
                .description("test")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .status("Done")
                .user(User.builder().idUser(UUID.randomUUID()).build())
                .build();

	optionalTask = Optional.of(taskDomain);
	optionalUser = Optional.of(User.builder().build());
    }

    @Test
    void getByIdTest() {
	when(taskRepository.findById(taskDomain.getIdTask())).thenReturn(optionalTask);
	var response = taskService.getById(taskDomain.getIdTask());
	assertThat(response).isNotNull();
    }

    @Test
    void getByIdTestException() {
	UUID generated = UUID.randomUUID();
	Assertions.assertThrows(NotFoundException.class, () -> taskService.getById(generated));
    }

    @Test
    void getAllTest() {
	when(taskRepository.findAll()).thenReturn(List.of(taskDomain));
	var response = taskService.getAll();
	assertThat(response).isNotEmpty();
    }

    @Test
    void saveTest() {
	when(taskRepository.save(any())).thenReturn(taskDomain);
	var response = taskService.save(taskDto);
	assertThat(response.getIdTask()).isEqualTo(taskDomain.getIdTask());
    }

    @Test
    void saveTestException() {
	when(taskRepository.findById(any())).thenReturn(optionalTask);
	Assertions.assertThrows(SaveException.class, () -> taskService.save(taskDto));
    }

    @Test
    void updateTest() {
	when(taskRepository.findById(any())).thenReturn(optionalTask);
	when(userRepository.findById(any())).thenReturn(optionalUser);
	when(taskRepository.save(any())).thenReturn(taskDomain);
	var response = taskService.update(taskDto);
	assertThat(response.getIdTask()).isEqualTo(taskDomain.getIdTask());
    }

    @Test
    void updateTestException() {
	when(taskRepository.findById(any())).thenReturn(Optional.empty());
	when(taskRepository.save(any())).thenReturn(taskDomain);
	Assertions.assertThrows(UpdateException.class, () -> taskService.update(taskDto));
    }

    @Test
    void deleteTest() {
	when(taskRepository.findById(any())).thenReturn(optionalTask);
	doNothing().when(taskRepository).deleteById(taskDto.getIdTask());
	taskService.delete(taskDto.getIdTask());
	verify(taskRepository, times(1)).delete(any());
    }

    @Test
    void deleteTestException() {
	var idTask = taskDto.getIdTask();
	when(taskRepository.findById(any())).thenReturn(Optional.empty());
	doNothing().when(taskRepository).deleteById(idTask);
	Assertions.assertThrows(NotFoundException.class, () -> taskService.delete(idTask));
    }

}
