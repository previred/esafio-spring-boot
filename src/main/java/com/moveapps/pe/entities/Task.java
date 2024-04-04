package com.moveapps.pe.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_task")
public class Task {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "task_state", referencedColumnName = "id")
	private TaskState taskState;

	@ManyToOne
	@JoinColumn(name = "_user", referencedColumnName = "id")
	private User user;

	@Column(name = "creation_date")
	private Date creationDate;

}
