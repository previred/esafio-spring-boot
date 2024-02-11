package com.previred.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.previred.challenge.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
	@Query("SELECT r FROM Roles r WHERE r.name = :name")
	Roles findRolByName(@Param("name") String name);
}
