package cl.dpichinil.desafio.desafiospringboot.dto;

import cl.dpichinil.desafio.desafiospringboot.util.FillObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoTareaDtoTest {

    @Test
    void fillObject(){
        EstadoTareaDto dto = FillObject.fillEstadoTareaDto();
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals("description", dto.getDescripcion());
        Assertions.assertTrue(dto.isActivo());
    }
}