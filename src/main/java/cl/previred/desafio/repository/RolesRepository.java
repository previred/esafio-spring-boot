package cl.previred.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cl.previred.desafio.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    @Query("SELECT r FROM Roles r WHERE r.name = :name")
    Roles findRolByName(@Param("name") String name);
}
