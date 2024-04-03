package com.nuevospa.app.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuevospa.app.SetupTest;
import com.nuevospa.app.dtos.request.TaskRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskIntegrationTest extends SetupTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private TaskRequestDto createTask;
    private TaskRequestDto createTaskBlankTitle;
    private TaskRequestDto createTaskBadStatus;
    private TaskRequestDto updateTask;
    @Autowired
    public TaskIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    public void init() {
        createTask = TaskRequestDto.builder().title("Task N°1").detail("Create API").status("Completed").build();
        createTaskBlankTitle = TaskRequestDto.builder().title(null).detail(null).status(null).build();
        createTaskBadStatus = TaskRequestDto.builder().title("Task N°1").detail("Create API").status("Other").build();
        updateTask = TaskRequestDto.builder().title("Task N°1").detail("Edit API").status("Pending").build();
    }

    @Test
    @Order(1)
    public void createTaskIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(TASK_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(createTask)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value(createTask.getTitle()))
                .andExpect(jsonPath("$.detail").value(createTask.getDetail()))
                .andExpect(jsonPath("$.createAt").exists())
                .andExpect(jsonPath("$.deleteAt").doesNotExist())
                .andExpect(jsonPath("$.taskStatus.id").value(3))
                .andExpect(jsonPath("$.taskStatus.name").value(createTask.getStatus()));
    }
    @Test
    @Order(2)
    public void createTaskWithEmptyTitleIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(TASK_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(createTaskBlankTitle)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.message").value("Invalid arguments error."))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("Title is required"));
    }
    @Test
    @Order(3)
    public void getTasksIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(TASK_ENDPOINT)
                        .header("Authorization", "Bearer " + token)
                        .contentType(JSON_CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE));
    }
    @Test
    @Order(4)
    public void getTaskIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(TASK_ENDPOINT + "/1")
                        .contentType(JSON_CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value(createTask.getTitle()))
                .andExpect(jsonPath("$.detail").value(createTask.getDetail()))
                .andExpect(jsonPath("$.createAt").exists())
                .andExpect(jsonPath("$.deleteAt").doesNotExist())
                .andExpect(jsonPath("$.taskStatus.id").value(3))
                .andExpect(jsonPath("$.taskStatus.name").value(createTask.getStatus()));
    }
    @Test
    @Order(5)
    public void getTaskeNonExistingIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(TASK_ENDPOINT + "/2")
                        .contentType(JSON_CONTENT_TYPE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.message").value("Not found."))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("Task with id  2 not found."));
    }

    @Test
    @Order(6)
    public void updateNonExistingTaskIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(TASK_ENDPOINT + "/2")
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(createTask)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.message").value("Not found."))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("Task with id  2 not found."));
    }

    @Test
    @Order(7)
    public void deleteNonExistingTaskIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(TASK_ENDPOINT + "/2")
                        .contentType(JSON_CONTENT_TYPE))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.message").value("Not found."))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("Task with id  2 not found."));
    }
    @Test
    @Order(8)
    public void createTaskInvalidStatusIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(TASK_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(createTaskBadStatus)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.message").value("Invalid arguments error."))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("Invalid task status identifier ('Pending','In progress','Completed')"));
    }

    @Test
    @Order(9)
    public void updateTaskIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(TASK_ENDPOINT + "/1")
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(updateTask)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value(updateTask.getTitle()))
                .andExpect(jsonPath("$.detail").value(updateTask.getDetail()))
                .andExpect(jsonPath("$.createAt").exists())
                .andExpect(jsonPath("$.deleteAt").doesNotExist())
                .andExpect(jsonPath("$.taskStatus.id").value(1))
                .andExpect(jsonPath("$.taskStatus.name").value(updateTask.getStatus()));
    }

    @Test
    @Order(10)
    public void updateTaskInvalidStatusIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(TASK_ENDPOINT+ "/1")
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(createTaskBadStatus)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.message").value("Invalid arguments error."))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("Invalid task status identifier ('Pending','In progress','Completed')"));
    }
    @Test
    @Order(11)
    public void deleteTaskIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(TASK_ENDPOINT + "/1")
                        .contentType(JSON_CONTENT_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.detail").exists())
                .andExpect(jsonPath("$.createAt").exists())
                .andExpect(jsonPath("$.deleteAt").exists());
    }

}
