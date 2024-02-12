package org.openapitools.repository;


import org.openapitools.dto.User;
import org.openapitools.dto.UserPaginated;

import java.util.List;

public interface UserRepository {

    User save(User user);
    List<User> getAll();
    UserPaginated getAllPaginated(Integer size, Integer page);
}
