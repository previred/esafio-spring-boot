package cl.corellana.taskmanager.persistence.entities;

import cl.corellana.taskmanager.api.model.SignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity dtoToEntity(SignUpRequest dto);

}
