package co.com.task.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import co.com.task.api.dto.LoginResponseDTO;
import co.com.task.api.service.SessionService;

@WebMvcTest
@ContextConfiguration(classes = { LoginController.class })
class LoginControllerTest extends GeneralContextMock {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;
    @MockBean
    SessionService loginService;
    LoginResponseDTO loginResponse;

    @BeforeEach
    void init() {
        openMocks(this);
	loginResponse = LoginResponseDTO.builder().token("Token").build();
	mockSecurityContext();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();

    }

    @Test
    void authenticationTest() throws Exception {
	when(loginService.login(any())).thenReturn(loginResponse);
        var response = mockMvc
                .perform(
			post("/authentication")
                                .contentType(APPLICATION_JSON_VALUE)
                                .content("""
                                        {
					"email": "edwin",
					"pass": "dfvdffedr45"
					    
                                        }""")
                )
		.andExpect(status().isOk())
                .andReturn();

        var actual = response.getResponse().getContentAsString();
        assertThat(actual).isNotNull();
    }
    


}
