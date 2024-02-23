package cl.dpichinil.desafio.desafiospringboot.dto;

import cl.dpichinil.desafio.desafiospringboot.util.FillObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TareaDtoTest {
    @Test
    void fillObject(){
        TareaDto dto = FillObject.fillTareaDto();
        dto.setEstadoTarea(FillObject.fillEstadoTareaDto());

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(1, dto.getId());
        Assertions.assertEquals("tarea 1", dto.getDescripcion());
        Assertions.assertTrue(dto.isActivo());
        Assertions.assertNotNull( dto.getEstadoTarea());
    }
}