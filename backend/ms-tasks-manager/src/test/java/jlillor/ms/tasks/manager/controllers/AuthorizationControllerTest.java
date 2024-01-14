package jlillor.ms.tasks.manager.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Pruebas unitarias para el controlador de autorización.
 *
 * @author Juan Lillo
 * @version 1.0
 * @since 1.0
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationControllerTest {

    /** mock. */
    @Autowired
    private MockMvc mockMvc;

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
     * Test de login.
     *
     * @param index {@link Integer} índice
     * @throws Exception excepción
     */
    @ParameterizedTest
    @CsvSource(delimiterString = ";", value = {
            "1;{\"email\" : \"usuario1@mail.com\", \"password\": \"Cambiar1234\"}",
            "2;{\"email\" : \"usuario1@mail.com\", \"password\": \"Cambiar12234\"}",
            "3;{\"email\" : \"usuario@mail.com\", \"password\": \"Cambiar1234\"}",
            "4;{\"email\" : \"usuario@mail.com\", \"password\": \"xzcc\"}",
            "5;{\"email\" : \"@mail.com\", \"password\": \"Cambiar1234\"}",
            "6;{ null }",
    })
    void testLogin(final int index, final String request) throws Exception {
        final var httpRequest = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request));
        switch (index) {
            case 2:
                httpRequest.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("US01")))
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
                break;
            case 3:
                httpRequest.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("NF01")))
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
                break;
            case 4:
            case 5:
                httpRequest.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("RE02")))
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
                break;
            case 6:
                httpRequest.andExpect(MockMvcResultMatchers.status().is4xxClientError())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("RE01")))
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
                break;
            default:
                httpRequest.andExpect(MockMvcResultMatchers.status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is("00")))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.is(Matchers.notNullValue())))
                        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
                break;
        }
    }

}