package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.dto.JwtUser;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtServiceTest {
    @InjectMocks
    JwtService jwtService;
    @Test
    void generateToken() {
        String username ="username";
        String token = jwtService.generateToken(username);
        Assertions.assertNotNull(token);
    }

    @Test
    void extractUsername() {
        String username ="username";
        String token = jwtService.generateToken(username);
        String username2 = jwtService.extractUsername(token);
        Assertions.assertEquals(username, username2);
    }

    @Test
    void extractExpiration() {
        String username ="username";
        String token = jwtService.generateToken(username);
        Date date = jwtService.extractExpiration(token);
        Assertions.assertNotNull(date);
    }

    @Test
    void extractClaim() {
        String username ="username";
        String token = jwtService.generateToken(username);
        String username2 = jwtService.extractClaim(token, Claims::getSubject);
        Assertions.assertEquals(username, username2);
    }

    @Test
    void validateToken() {
        String username ="username";
        JwtUser jwtUser = FillObject.fillJwtUser();
        String token = jwtService.generateToken(username);
        Assertions.assertTrue( jwtService.validateToken(token, jwtUser));
    }
}