package co.com.task.api.service;

import java.util.List;
import java.util.UUID;

import co.com.task.api.dto.UserDTO;

public interface UserService {

    public UserDTO getById(UUID userId);

    public List<UserDTO> getAll();

    public UserDTO save(UserDTO user);

    public UserDTO update(UserDTO user);

    public void delete(UUID userId);

}
