package com.enterprise.move.apps.MoveApps.repository;


import com.enterprise.move.apps.MoveApps.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {

    User findByName(String name);
}
