package org.openapitools.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openapitools.dto.Task;
import org.openapitools.entity.LoginEntity;
import org.openapitools.entity.TaskEntity;
import org.openapitools.entity.TaskStatusEntity;
import org.openapitools.entity.UserEntity;
import org.openapitools.exceptions.NotFoundException;
import org.openapitools.repository.adapter.TaskAdapter;
import org.openapitools.repository.jpa.TaskEntityRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(SpringExtension.class)
public class TaskRepositoryTest {

    @MockBean
    TaskEntityRepository taskEntityRepository;
    TaskRepository taskRepository;

    Task task;
    TaskEntity taskEntity;

    @BeforeEach
    void init() {
        openMocks(this);

        task = Task.builder()
                .taskId(UUID.randomUUID())
                .description("test")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .name("name test")
                .taskStatus("Terminada")
                .userId(UUID.randomUUID())
                .taskStatusId(UUID.randomUUID())
                .build();

        taskEntity = TaskEntity.builder()
                .taskId(UUID.randomUUID())
                .description("test")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .taskStatus(TaskStatusEntity.builder().description("test").build())
                .user(UserEntity.builder().userId(UUID.randomUUID())
                        .login(LoginEntity.builder().email("test@test.com").build()).build())
                .build();

        taskRepository = new TaskAdapter(taskEntityRepository);
    }

    @Test
    void getAll() {
        when(taskEntityRepository.findAll()).thenReturn(List.of(taskEntity));
        var response = taskRepository.getAll();
        assertThat(response).isNotEmpty();
    }

    @Test
    void save() {
        when(taskEntityRepository.save(any(TaskEntity.class))).thenReturn(taskEntity);
        var response = taskRepository.save(task);
        assertThat(response).isNotNull();
        assertThat(response.getTaskId()).isEqualTo(taskEntity.getTaskId());
    }

    @TestFactory
    Stream<DynamicTest> getById() {
        return Stream.of(
                dynamicTest("getById OK", () -> {
                    when(taskEntityRepository.findById(any(UUID.class)))
                            .thenReturn(Optional.of(taskEntity));
                    var response = taskRepository.getById(UUID.randomUUID());
                    assertThat(response).isNotNull();
                    assertThat(response.getTaskId()).isEqualTo(taskEntity.getTaskId());
                }),
                dynamicTest("throws exception", () -> {
                    when(taskEntityRepository.findById(any(UUID.class)))
                            .thenReturn(Optional.empty());
                    assertThrows(NotFoundException.class,
                            () -> taskRepository.getById(UUID.randomUUID()));
                })
        );
    }

    @Test
    void delete() {
        var taskId = UUID.randomUUID();
        doNothing().when(taskEntityRepository).deleteById(taskId);
        taskRepository.delete(taskId);
        verify(taskEntityRepository,times(1)).deleteById(taskId);
    }
}
