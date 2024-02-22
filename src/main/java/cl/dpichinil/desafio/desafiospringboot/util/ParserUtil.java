package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.dto.EstadoTareaDto;
import cl.dpichinil.desafio.desafiospringboot.dto.TareaDto;
import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.EstadoTarea;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.Tarea;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;

import java.util.Date;

public class ParserUtil {
    public static UserDto parseUserToUserDto(User entity){
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .lastPasswordChange(entity.getLastPasswordChange())
                .createdDate(entity.getCreatedDate())
                .lastAccess(entity.getLastAccess())
                .active(entity.isActive())
                .expired(entity.isExpired())
                .locked(entity.isLocked())
                .authoritiesText(entity.getAuthoritiesText())
                .build();
    }
    public static UserDto parseUserToUserDtoWithPassword(User entity){
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .lastPasswordChange(entity.getLastPasswordChange())
                .createdDate(entity.getCreatedDate())
                .lastAccess(entity.getLastAccess())
                .active(entity.isActive())
                .expired(entity.isExpired())
                .locked(entity.isLocked())
                .authoritiesText(entity.getAuthoritiesText())
                .build();
    }

    public static Tarea parseTareaDtoToTarea(TareaDto dto) {
        return Tarea.builder()
                .id(dto.getId())
                .descripcion(dto.getDescripcion())
                .activo(dto.isActivo())
                .build();
    }

    public static TareaDto parseTareaToTareaDto(Tarea entity) {
        return TareaDto.builder()
                .id(entity.getId())
                .descripcion(entity.getDescripcion())
                .activo(entity.isActivo())
                .build();
    }
    public static TareaDto parseTareaToTareaDtoWithEstado(Tarea entity) {
        TareaDto dto = parseTareaToTareaDto(entity);
        dto.setEstadoTarea( parseEstadoTareaToEstadoTareaDto(entity.getEstadoTarea()));
        return dto;
    }
    public static EstadoTareaDto parseEstadoTareaToEstadoTareaDto(EstadoTarea entity) {
        return EstadoTareaDto.builder()
                .id(entity.getId())
                .descripcion(entity.getDescripcion())
                .activo(entity.isActivo())
                .build();
    }
}
