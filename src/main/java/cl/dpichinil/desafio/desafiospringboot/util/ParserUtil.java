package cl.dpichinil.desafio.desafiospringboot.util;

import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
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
}
