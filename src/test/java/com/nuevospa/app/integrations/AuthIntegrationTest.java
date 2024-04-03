package com.nuevospa.app.integrations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuevospa.app.SetupTest;
import com.nuevospa.app.dtos.request.SignInRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthIntegrationTest extends SetupTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private SignInRequestDto signInSuccess;
    private SignInRequestDto signInIncorrectCredentials;
    private SignInRequestDto signInInvalidEmail;
    private SignInRequestDto signInWithBlankEmail;
    private SignInRequestDto signInWithBlankPassword;
    private SignInRequestDto signInWithBlankPasswordAndEmail;

    @Autowired
    AuthIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @BeforeEach
    void init() {
        signInSuccess = SignInRequestDto.builder().email("sudo@vicente-munoz.cl").password("demo123").build();
        signInIncorrectCredentials = SignInRequestDto.builder().email("sudo@vicente-munoz.cl").password("demo123.").build();
        signInInvalidEmail = SignInRequestDto.builder().email("----").password("demo123.").build();
        signInWithBlankEmail = SignInRequestDto.builder().email("").password("demo123.").build();
        signInWithBlankPassword = SignInRequestDto.builder().email("sudo@vicente-munoz.cl").password("").build();
        signInWithBlankPasswordAndEmail = SignInRequestDto.builder().email("").password("").build();
    }

    @Test
    void signInSuccessIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_IN_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(signInSuccess)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.accessToken").isString())
                .andExpect(jsonPath("$.expirationDate").isString())
                .andExpect(jsonPath("$.tokenType").value("Bearer"));
    }

    @Test
    void signInUnauthorizedIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_IN_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(signInIncorrectCredentials)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.errors[0]").value("Invalid username or password"))
                .andExpect(jsonPath("$.message").value("Authentication error"));
    }

    @Test
    void signInWithInvalidEmailIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_IN_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(signInInvalidEmail)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.errors[0]").value("Email must have a valid format"))
                .andExpect(jsonPath("$.message").value("Invalid arguments error."));
    }

    @Test
    void signInWithBlankEmailIntegrationTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_IN_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(signInWithBlankEmail)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.errors[0]").value("Email is required"))
                .andExpect(jsonPath("$.message").value("Invalid arguments error."));
    }

    @Test
    void signInWithBlankPasswordIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_IN_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(signInWithBlankPassword)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.errors[0]").value("Password is required"))
                .andExpect(jsonPath("$.message").value("Invalid arguments error."));
    }

    @Test
    void signInWithBlankPasswordAndEmailIntegrationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(SIGN_IN_ENDPOINT)
                        .contentType(JSON_CONTENT_TYPE)
                        .content(objectMapper.writeValueAsString(signInWithBlankPasswordAndEmail)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.payload").isEmpty())
                .andExpect(jsonPath("$.errors[0]").isString())
                .andExpect(jsonPath("$.errors[1]").isString())
                .andExpect(jsonPath("$.message").value("Invalid arguments error."));
    }
}