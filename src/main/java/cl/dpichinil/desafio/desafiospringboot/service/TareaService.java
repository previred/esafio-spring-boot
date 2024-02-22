package cl.dpichinil.desafio.desafiospringboot.service;

import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.TareaDto;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.Tarea;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface TareaService {
    ResponseEntity<ResponseDto> create(TareaDto Dto);
    ResponseEntity<ResponseDto> getAll();
    ResponseEntity<ResponseDto> update(TareaDto Dto);
    ResponseEntity<ResponseDto> delete(int id);
    ResponseEntity<ResponseDto> getAllEstadoTarea();
}
