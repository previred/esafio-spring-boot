package co.com.task.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import co.com.task.api.configtest.GeneralContextMock;
import co.com.task.api.dto.TaskDTO;
import co.com.task.api.service.TaskService;

@WebMvcTest
@ContextConfiguration(classes = {TaskController.class})
class TaskControllerTest extends GeneralContextMock {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    TaskService taskService;

    TaskDTO task;
    String geretareUUID;

    @BeforeEach
    void init() {
        openMocks(this);
	geretareUUID = UUID.randomUUID().toString();
        mockSecurityContext();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        task = TaskDTO.builder()
                .idTask(UUID.randomUUID())
		.description("test task")
                .name("name test")
                .email("email test")
                .status("Done")
                .idUser(UUID.randomUUID())
                .build();
    }

    @Test
    void getByIdTest() throws Exception {
	when(taskService.getById(any(UUID.class))).thenReturn(task);
        var response = mockMvc
                .perform(
			get("/task/task-id/".concat(geretareUUID))
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllTest() throws Exception {
	when(taskService.getAll()).thenReturn(List.of(task));
        var response = mockMvc
                .perform(
			get("/task")
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void saveTest() throws Exception {
        when(taskService.getById(any(UUID.class))).thenReturn(task);
        var response = mockMvc
                .perform(
                        post("/task/save")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("""
                                        {
					    "description": "Test prueba",
					    "status": "NOT DONE",
                                            "idUser": "954cdc80-57e3-46b0-ae31-4173671bd5e4"
					    
                                        }""")
                )
                .andExpect(status().isCreated())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }
    
    @Test
    void updateTest() throws Exception {
        when(taskService.getById(any(UUID.class))).thenReturn(task);
        var response = mockMvc
                .perform(
			put("/task/update")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("""
                                        {
					    "description": "Test prueba Update ",
					    "status": "NOT DONE",
                                            "idUser": "954cdc80-57e3-46b0-ae31-4173671bd5e4"
					    
                                        }""")
                )
                .andExpect(status().isOk())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void deleteByIdTest() throws Exception {
        when(taskService.getById(any(UUID.class))).thenReturn(task);
        mockMvc
                .perform(
			delete("/task/delete/".concat(geretareUUID))
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

}
