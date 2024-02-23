package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.dto.JwtUser;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SecurityFunctionTest {
    SecurityFunction securityFunction;
    @Test
    void getHashText() {
        String username = "dpichinil";
        String password = "123456";
        String expected = String.format("%s:%s", username, password);
        String hashText = SecurityFunction.getHashText(username, password);
        Assertions.assertEquals(expected, hashText);
    }

    @Test
    void getHeaderToken() {
    }

    @Test
    void quitaBarer() {
        String token = "Bearer 123456";
        String response = SecurityFunction.quitaBarer(token);
        Assertions.assertEquals("123456", response);
    }

    @Test
    void getToken() {

    }

    @Test
    void parseUserToJwtUser() {
        User user = FillObject.fillUser();
        JwtUser jwtUser = SecurityFunction.parseUserToJwtUser(user);
        Assertions.assertNotNull(jwtUser);
    }
}