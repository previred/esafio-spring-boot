package com.company.persistence;

import com.company.model.Task;
import com.company.persistence.task.TaskDataProvider;
import com.company.persistence.task.TaskEntity;
import com.company.persistence.task.TaskMapper;
import com.company.persistence.task.TaskRepository;
import com.company.persistence.task.status.TaskStatusEntity;
import com.company.persistence.users.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class TaskDataProviderTest {

    @InjectMocks
    private TaskDataProvider taskDataProvider;

    @Mock
    private  TaskRepository taskRepository;

    @Mock
    private  TaskMapper taskMapper;

    private TaskEntity taskEntity;

    private Task task;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        taskEntity = TaskEntity.builder()
                .uuid(UUID.randomUUID())
                .taskStatus(TaskStatusEntity.builder().build())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .user(UserEntity.builder().build())
                .description("Tarea de prueba")
                .build();

        task = Task.builder()
                .uuid(UUID.randomUUID())
                .taskStatusId(UUID.randomUUID())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .userId(UUID.randomUUID())
                .description("Tarea de prueba")
                .build();
    }

    @Test
    public  void createTask() {
        mockMapperToEntity();
        taskDataProvider.create(task);
        Mockito.verify(taskRepository).save(Mockito.any());
    }

    @Test
    public void findById(){
        Mockito.when(taskRepository.findById(Mockito.any())).thenReturn(Optional.of(taskEntity));
        mockMapperToModel();
        Optional<Task> optionalTask = taskDataProvider.findById(UUID.randomUUID());
        Assertions.assertTrue(optionalTask.isPresent());
    }

    @Test
    public void getAllTask(){
        Mockito.when(taskRepository.findAll()).thenReturn(Collections.singletonList(taskEntity));
        mockMapperToModel();
        Assertions.assertNotNull(taskDataProvider.getAll());
    }

    @Test
    public void delete(){
        taskDataProvider.delete(UUID.randomUUID());
        Mockito.verify(taskRepository).deleteById(Mockito.any());
    }

    private void mockMapperToEntity() {
        Mockito.when(taskMapper.toEntity(Mockito.any())).thenReturn(taskEntity);
    }

    private void mockMapperToModel() {
        Mockito.when(taskMapper.toModel(Mockito.any())).thenReturn(task);
    }


}
