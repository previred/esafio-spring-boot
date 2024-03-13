package cl.previred.challenge.service;

import cl.previred.challenge.exception.UserCreateException;
import cl.previred.challenge.exception.UserNotFoundException;
import cl.previred.challenge.model.User;
import cl.previred.challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    validateUser(user);

    return userRepository.save(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
  }

  public User updateUser(Long id, User userDetails) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

    user.setName(userDetails.getName());
    user.setEmail(userDetails.getEmail());

    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    userRepository.delete(user);
  }

  private void validateUser(User user) {
    if (!StringUtils.hasText(user.getName())) {
      throw new UserCreateException("El nombre del usuario es obligatorio.");
    }
    if (!StringUtils.hasText(user.getEmail()) || !user.getEmail().contains("@")) {
      throw new UserCreateException("Se requiere un correo electrónico válido.");
    }
  }
}
