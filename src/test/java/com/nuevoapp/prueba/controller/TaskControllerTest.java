package com.nuevoapp.prueba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.nuevoapp.prueba.domain.model.dto.TasksDto;
import com.nuevoapp.prueba.domain.model.dto.UserDto;
import com.nuevoapp.prueba.domain.service.TasksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static com.nuevoapp.prueba.utils.MockMvcUtils.getJacksonMessageConverterInstance;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    protected MockMvc mvc;

    @Mock
    private TasksService tasksService;

    @InjectMocks
    private TasksController controller;

    @BeforeEach
    public void setUp() {
        mvc =
                standaloneSetup(controller)
                        .setMessageConverters(getJacksonMessageConverterInstance())
                        .build();
    }

    @Test
    void testGetTaskById() throws Exception {
        // Mock data
        int taskId = 1;
        TasksDto tasksDto = new TasksDto();

        when(tasksService.getTaskById(eq(taskId)))
                .thenReturn(tasksDto);

        mvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void testGetTasksByEmail() throws Exception {

        String userEmail = "test@example.com";
        TasksDto tasksDto = new TasksDto();
        when(tasksService.getTaskByEmail(userEmail))
                .thenReturn(Collections.singletonList(tasksDto));

        mvc.perform(MockMvcRequestBuilders.get("/tasks/user/{email}", userEmail)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void testCreateTask() throws Exception {

        TasksDto tasksDto = new TasksDto();
        UserDto userDto = new UserDto();
        userDto.setEmail("email");
        tasksDto.setUser(userDto);
        when(tasksService.createTask(any(TasksDto.class)))
                .thenReturn(tasksDto);

        mvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tasksDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateTaskBatch() throws Exception {
        TasksDto tasksDto = new TasksDto();
        UserDto userDto = new UserDto();
        userDto.setEmail("email");
        tasksDto.setUser(userDto);

        List<TasksDto> taskList = Collections.singletonList(tasksDto);

        when(tasksService.createTaskBatch(anyList()))
                .thenReturn(taskList);

        mvc.perform(MockMvcRequestBuilders.post("/tasks/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(taskList)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testUpdateTaskById() throws Exception {
        TasksDto tasksDto = new TasksDto();
        UserDto userDto = new UserDto();
        userDto.setEmail("email");
        tasksDto.setUser(userDto);

        when(tasksService.updateTaskById(any(TasksDto.class)))
                .thenReturn(tasksDto);

        mvc.perform(MockMvcRequestBuilders.put("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(tasksDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testPatchTaskById() throws Exception {

        int taskId = 1;
        JsonPatch patchOperations = new JsonPatch(List.of(new JsonPatchOperation[]{}));
        TasksDto tasksDto = new TasksDto();

        when(tasksService.patchTaskById(eq(taskId), any(JsonPatch.class)))
                .thenReturn(tasksDto);

        mvc.perform(MockMvcRequestBuilders.patch("/tasks/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patchOperations)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testDeleteTaskById() throws Exception {
        int taskId = 1;

        when(tasksService.deleteTaskById(eq(taskId)))
                .thenReturn(true);

        mvc.perform(MockMvcRequestBuilders.delete("/tasks/{id}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
