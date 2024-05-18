package co.com.task.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

import javax.validation.Validator;

import org.springframework.stereotype.Service;

import co.com.task.api.domain.User;
import co.com.task.api.dto.UserDTO;
import co.com.task.api.exceptions.ExceptionManager;
import co.com.task.api.mapper.UserMapper;
import co.com.task.api.repository.UserRepository;
import co.com.task.api.utility.Utilities;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository, Validator validator) {
	this.userRepository = userRepository;
	this.validator = validator;
    }

    @Override
    public UserDTO getById(UUID userId) {
	return UserMapper.userDomainToUserDto(
		userRepository.findById(userId)
			.orElseThrow(() -> new ExceptionManager().new NotFoundException("User")));
    }

    @Override
    public List<UserDTO> getAll() {
	return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(UserMapper::userDomainToUserDto)
		.toList();
    }

    @Override
    public UserDTO save(UserDTO userDto) {
	User user = UserMapper.userDtoToUserDomain(userDto);
	Utilities.validate(user, validator, "User");

	if (Optional.ofNullable(userDto.getIdUser()).isPresent()
		&& userRepository.findById(userDto.getIdUser()).isPresent()) {
	    throw new ExceptionManager().new SaveException(
		    "Ya existe el usuario ingresado: ".concat(userDto.getIdUser().toString()));
	}

	if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
	    throw new ExceptionManager().new SaveException(
		    "Ya existe el registro con el email: ".concat(user.getEmail()));
	}
	return UserMapper.userDomainToUserDto(userRepository.save(user));
    }

    @Override
    public UserDTO update(UserDTO userDto) {

	UUID idUser = Optional.ofNullable(userDto.getIdUser())
		.orElseThrow(() -> new ExceptionManager().new EmptyFieldException("El idUser no puede estar vacio"));

	User user = userRepository.findById(idUser)
		.orElseThrow(() -> new ExceptionManager().new UpdateException(userDto.getIdUser().toString()));

	user = UserMapper.updteUserDtoToUserDomain(user, userDto);
	Utilities.validate(user, validator, "User");
	return UserMapper.userDomainToUserDto(userRepository.save(user));
    }

    @Override
    public void delete(UUID userId) {
	User user = userRepository.findById(userId)
		.orElseThrow(() -> new ExceptionManager().new NotFoundException(userId.toString()));
	userRepository.delete(user);
    }

}
