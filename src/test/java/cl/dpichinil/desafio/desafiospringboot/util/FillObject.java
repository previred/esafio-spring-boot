package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.dto.*;
import cl.dpichinil.desafio.desafiospringboot.persistence.entity.User;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FillObject {
    public static ResponseDto fillResponseDto(){
        return ResponseDto.builder()
                .code(0)
                .message("OK")
                .build();
    }

    public static UserDto fillUserDto() {
        return UserDto.builder()
                .id(1)
                .username("username")
                .password("password")
                .lastPasswordChange(new Date())
                .createdDate(new Date())
                .lastAccess(new Date())
                .authoritiesText("USER,ADMIN")
                .active(true)
                .locked(false)
                .expired(false)
                .build();
    }

    public static TareaDto fillTareaDto() {
        return TareaDto.builder()
                .id(1)
                .descripcion("tarea 1")
                .activo(true)
                .build();
    }

    public static List<TareaDto> fillListTareaDto() {
        List<TareaDto> list = new ArrayList<>();
        list.add(fillTareaDto());
        return list;
    }

    public static EstadoTareaDto fillEstadoTareaDto() {
        return EstadoTareaDto.builder()
                .id(1)
                .descripcion("description")
                .activo(true)
                .build();
    }

    public static JwtUser fillJwtUser() {
        return new JwtUser(
                "username",
                "password",
                "USER,ADMIN",
                true,
                true,
                true,
                true
        );
    }

    public static User fillUser() {
        return User.builder()
                .id(1)
                .username("username")
                .password("password")
                .lastPasswordChange(new Date())
                .createdDate(new Date())
                .lastAccess(new Date())
                .authoritiesText("USER,ADMIN")
                .active(true)
                .locked(false)
                .expired(false)
                .build();
    }
}
