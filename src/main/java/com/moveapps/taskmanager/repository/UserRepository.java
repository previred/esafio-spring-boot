package com.moveapps.taskmanager.repository;

import com.moveapps.taskmanager.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String externalId);
    User findByUsernameAndPassword(String username, String password);

}
