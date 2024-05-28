package cl.previred.desafio.service.impl;

import cl.previred.desafio.entity.TaskEntity;
import cl.previred.desafio.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceImplTest {

    @InjectMocks
    private TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void shouldListTasks_Ok(){
        List<TaskEntity> list = taskService.findAll();
        assertNotNull(list);
    }

    @Test
    public void shouldSaveTask_Ok(){
        when(taskRepository.save(any())).thenReturn(mockTask());
        TaskEntity task = taskService.createTask(mockTask());
        assertNotNull(task);
    }

    @Test
    public void shouldNotGetTask_invalidUUID(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.findById(UUID.fromString("abc"));
        });
        String expectedMessage = "Invalid UUID string";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * mock data
     */

    private static final UUID mockuuid = UUID.fromString("c7a642f1-a413-4f32-8d98-27b9b3c1ff10");

    private TaskEntity mockTask() {
        return TaskEntity.builder().id(mockuuid).description("Mock task").build();
    }
}