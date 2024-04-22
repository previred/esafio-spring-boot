package com.company.persistence.users;


import com.company.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User toModel(UserEntity input);
}
