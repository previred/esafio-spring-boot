package jlillor.ms.tasks.manager.controllers;

import jlillor.ms.tasks.manager.exceptions.MsBadRequestException;
import jlillor.ms.tasks.manager.fixtures.MsFixtures;
import jlillor.ms.tasks.manager.properties.MessageProperty;
import jlillor.ms.tasks.manager.repositories.TaskRepository;
import jlillor.ms.tasks.manager.services.AuthentificationService;
import jlillor.ms.tasks.manager.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Pruebas unitarias para el controlador de gestión de tareas.
 *
 * @author Juan Lillo
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskManagementControllerTest {

    /** mock. */
    @Autowired
    private MockMvc mockMvc;
    /** message property. */
    @Autowired
    private MessageProperty messageProperty;
    /** authentification service. */
    @MockBean
    private AuthentificationService authentificationService;
    /** user service. */
    @MockBean
    private UserService userService;
    /** task repository. */
    @MockBean
    private TaskRepository taskRepository;


    // -------------------------------------------------------------------
    // -- Setup ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Setup.
     */
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // -------------------------------------------------------------------
    // -- Tests ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Test de creación de tarea.
     *
     * @throws Exception excepción
     */
    @Test
    void testCreateTaskOK() throws Exception {
        Mockito.doNothing().when(authentificationService).validate(ArgumentMatchers.anyString());
        Mockito.when(userService.getUserByEmail(ArgumentMatchers.anyString())).thenReturn(MsFixtures.getUser());
        Mockito.when(userService.getUserByToken(ArgumentMatchers.anyString())).thenReturn(MsFixtures.getUser());
        Mockito.when(taskRepository.save(ArgumentMatchers.any())).thenReturn(MsFixtures.getTask());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(MsFixtures.CREATE_REQUEST)
                        .header("token", MsFixtures.TOKEN)).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("00")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.title", Matchers.is("a totally normal title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.user", Matchers.is("super user")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    /**
     * Test de creación de tarea con token no presente.
     *
     * @throws Exception excepción
     */
    @Test
    void testCreateTaskTokenNotPresentError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(MsFixtures.CREATE_REQUEST)).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("RE03")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    /**
     * Test de creación de tarea con token no válido.
     *
     * @throws Exception excepción
     */
    @Test
    void testCreateTaskTokenNotValid() throws Exception {
        Mockito.doThrow(new MsBadRequestException(messageProperty.getInvalidToken())).when(authentificationService).validate(ArgumentMatchers.anyString());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(MsFixtures.CREATE_REQUEST)
                        .header("token", "dwq6+5")).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("US02")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

}