package org.openapitools.service;

import org.openapitools.dto.User;
import org.openapitools.dto.UserPaginated;

import java.util.List;

public interface UserService {

    User save(User user);
    List<User> getAll();
    UserPaginated getAllPaginated(Integer size, Integer page);
}
