package cl.nuevo.spa.taskmanager.repository;

import cl.nuevo.spa.taskmanager.domain.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/** The interface User repository. */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  /**
   * Find by user name optional.
   *
   * @param userName the user name
   * @return the optional
   */
  Optional<UserEntity> findByUserName(String userName);
}
