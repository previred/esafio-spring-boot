package com.desafio.spring.ec.ds.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class UserRolePk {

	@Column(name="id_user",nullable = false)
    private Long idUser;
	
	@Column(name="id_role",nullable = false)
    private Long idRole;

    
}
