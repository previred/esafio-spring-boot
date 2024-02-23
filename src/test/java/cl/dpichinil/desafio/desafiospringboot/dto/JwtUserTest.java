package cl.dpichinil.desafio.desafiospringboot.dto;

import cl.dpichinil.desafio.desafiospringboot.util.FillObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUserTest {

    @Test
    void fillObject(){
        JwtUser dto = FillObject.fillJwtUser();
        Assertions.assertNotNull(dto);
        Assertions.assertEquals("username", dto.getUsername());
        Assertions.assertEquals("password", dto.getPassword());
        Assertions.assertNotNull(dto.getAuthorities());
        Assertions.assertTrue(dto.isAccountNonExpired());
        Assertions.assertTrue(dto.isAccountNonLocked());
        Assertions.assertTrue(dto.isCredentialsNonExpired());
        Assertions.assertTrue(dto.isEnabled());
    }
}