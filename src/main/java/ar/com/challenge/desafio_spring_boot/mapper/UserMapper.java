package ar.com.challenge.desafio_spring_boot.mapper;

import ar.com.challenge.desafio_spring_boot.dto.SignupDto;
import ar.com.challenge.desafio_spring_boot.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toEntity(SignupDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword()).build();
    }
}
