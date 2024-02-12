package org.openapitools.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.datatest.GeneralContextMock;
import org.openapitools.dto.Task;
import org.openapitools.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = {TaskController.class})
public class TaskControllerTest extends GeneralContextMock {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    TaskService taskService;

    Task task;

    @BeforeEach
    void init() {
        openMocks(this);
        mockSecurityContext();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        task = Task.builder()
                .taskId(UUID.randomUUID())
                .description("test")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .name("name test")
                .email("email test")
                .taskStatus("Terminada")
                .userId(UUID.randomUUID())
                .taskStatusId(UUID.randomUUID())
                .build();
    }

    @Test
    void getAll() throws Exception {
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
    void getById() throws Exception {
        when(taskService.getById(any(UUID.class))).thenReturn(task);
        var response = mockMvc
                .perform(
                        get("/task/task-id/".concat(UUID.randomUUID().toString()))
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void save() throws Exception {
        when(taskService.getById(any(UUID.class))).thenReturn(task);
        var response = mockMvc
                .perform(
                        post("/task/save")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("""
                                        {
                                            "description": "Lavar platos",
                                            "startDate": "2023-02-11T12:26:30.107",
                                            "endDate": "2023-02-11T12:26:30.107",
                                            "userId": "029db0e3-3c6a-4a33-81df-781ed267ecbd",
                                            "taskStatusId": "d8f17d2d-7f6a-482f-9c1d-353e1f730f69"
                                        }""")
                )
                .andExpect(status().isCreated())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void deleteById() throws Exception {
        when(taskService.getById(any(UUID.class))).thenReturn(task);
        mockMvc
                .perform(
                        delete("/task/delete/".concat(UUID.randomUUID().toString()))
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

}
