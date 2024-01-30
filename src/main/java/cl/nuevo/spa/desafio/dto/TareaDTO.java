package cl.nuevo.spa.desafio.dto;

import java.util.Date;

public record TareaDTO(Long id, String estado, Date fecha, UsuarioDTO usuario) {

}
