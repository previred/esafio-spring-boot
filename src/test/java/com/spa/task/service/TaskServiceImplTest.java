package com.spa.task.service;

import com.spa.task.entity.Task;
import com.spa.task.entity.TaskStatus;
import com.spa.task.entity.User;
import com.spa.task.exception.NotFoundException;
import com.spa.task.repository.TaskRepository;
import com.spa.task.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testFindAll() {

        when(taskRepository.findAll()).thenReturn(List.of(new Task(), new Task()));
        List<Task> result = taskService.findAll();
        assertEquals(2, result.size());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {

        when(taskRepository.findById(1L)).thenReturn(Optional.of(new Task()));
        Task result = taskService.findById(1L);
        assertNotNull(result);
        verify(taskRepository, times(1)).findById(1L);

    }

    @Test
    void testCreate() {

        Task task = Task.builder().id(1L).task("Sample Task").status(TaskStatus.builder().id(2L).build()).build();

        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(User.builder().id(1).username("admin").build()));

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "password");
        securityContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(securityContext);

        Task result = taskService.create(task);
        assertEquals(task, result);

    }

    @Test
    void testUpdate() {

        User user = new User();
        user.setId(1);
        user.setUsername("admin");

        Task task = Task.builder()
                .id(1L)
                .task("Sample Task")
                .status(TaskStatus.builder().id(2L).build())
                .user(user)
                .build();

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(userRepository.findByUsername(any(String.class))).thenReturn(Optional.of(user));

        Task updatedTask = Task.builder()
                .id(1L)
                .task("Updated Task")
                .status(TaskStatus.builder().id(3L).build())
                .user(user)
                .build();

        Task result = taskService.update(updatedTask, 1L);

        assertEquals(updatedTask.getTask(), result.getTask());
        assertEquals(task.getStatus().getId(), result.getStatus().getId());

    }

    @Test
    void testDelete() {

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(new Task()));
        taskService.delete(1L);
        Mockito.verify(taskRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testUpdateWhenTaskNotFound() {
        Long taskId = 1L;
        Task updatedTask = new Task();
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.update(updatedTask, taskId));
    }

    @Test
    void testDeleteWhenTaskNotFound() {
        Long taskId = 1L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.delete(taskId));
    }

    @Test
     void testGetUserIdWhenUserNotFound() {
        String username = "non_existing_user";
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> taskService.getUserId(username));
    }

}