package co.com.task.api.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import co.com.task.api.domain.Session;

public interface SessionRepository extends CrudRepository<Session, UUID> {

}
