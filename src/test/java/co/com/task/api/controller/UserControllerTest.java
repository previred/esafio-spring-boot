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
import co.com.task.api.dto.UserDTO;
import co.com.task.api.service.UserService;

@WebMvcTest
@ContextConfiguration(classes = {UserController.class})
class UserControllerTest extends GeneralContextMock {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    UserService userService;

    UserDTO user;
    String geretareUUID;

    @BeforeEach
    void init() {
        openMocks(this);
	geretareUUID = UUID.randomUUID().toString();
        mockSecurityContext();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

        user = UserDTO.builder()
		.idUser(UUID.randomUUID()).name("name").email("email").password("pass").build();
    }

    @Test
    void getByIdTest() throws Exception {
	when(userService.getById(any(UUID.class))).thenReturn(user);
        var response = mockMvc
                .perform(
			get("/user/user-id/".concat(geretareUUID))
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void getAllTest() throws Exception {
	when(userService.getAll()).thenReturn(List.of(user));
        var response = mockMvc
                .perform(
			get("/user")
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void saveTest() throws Exception {
        when(userService.getById(any(UUID.class))).thenReturn(user);
        var response = mockMvc
                .perform(
                        post("/user/save")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("""
                                        {
					"name": "Test prueba",
					"email": "edwinhh@hotmail.com",
					"password": "cferfe"
                                        }""")
                )
                .andExpect(status().isCreated())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }
    
    @Test
    void updateTest() throws Exception {
        when(userService.getById(any(UUID.class))).thenReturn(user);
        var response = mockMvc
                .perform(
			put("/user/update")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("""
                                        {
					    "name": "Test prueba Update ",
					"email": "edwin@hotmail.com",
					"password": "cferfe"
					    
                                        }""")
                )
                .andExpect(status().isOk())
                .andReturn();
        
        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }

    @Test
    void deleteByIdTest() throws Exception {
        when(userService.getById(any(UUID.class))).thenReturn(user);
        mockMvc
                .perform(
			delete("/user/delete/".concat(geretareUUID))
                                .contentType(APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk());
    }

}
