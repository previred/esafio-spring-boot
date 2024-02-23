package cl.dpichinil.desafio.desafiospringboot.dto;

import cl.dpichinil.desafio.desafiospringboot.util.FillObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    @Test
    void fillObject(){
        UserDto dto = FillObject.fillUserDto();

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals("username", dto.getUsername());
        Assertions.assertEquals("password", dto.getPassword());
        Assertions.assertNotNull(dto.getLastPasswordChange());
        Assertions.assertNotNull(dto.getCreatedDate());
        Assertions.assertNotNull(dto.getLastAccess());
        Assertions.assertTrue(dto.isActive());
        Assertions.assertFalse(dto.isLocked());
        Assertions.assertFalse(dto.isExpired());
        Assertions.assertEquals("USER,ADMIN", dto.getAuthoritiesText());
    }
}