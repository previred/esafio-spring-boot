package com.moveapps.security.user.applications.rest;

import static com.moveapps.commons.api.infraestructure.configs.Constants.REQUEST_MAPPING_BY_ID;
import static com.moveapps.security.user.infraestructure.confing.Constants.REQUEST_MAPPING_USERS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moveapps.security.user.domains.data.UserDTO;
import com.moveapps.security.user.domains.services.UserService;
import com.moveapps.security.user.infraestructure.adapters.UserAdapter;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping(REQUEST_MAPPING_USERS)
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserAdapter userAdapter;
	
	@ApiOperation("Search All Users")
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok()
							.body(userAdapter.toResponseBase(userService.getAll()));
	}
	@ApiOperation("Search User by id")
	@GetMapping(REQUEST_MAPPING_BY_ID)
	public ResponseEntity<?> getById(@PathVariable String id) {
		return ResponseEntity.ok()
							.body(userAdapter.toResponseBase(userService.getById(id)));
	}
	
	@ApiOperation("Save User")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok()
							.body(userAdapter.toResponseBase(userService.save(userAdapter.toEntity(userDTO))));
	}
	
	@ApiOperation("Update User")
	@PutMapping
	public ResponseEntity<?> update(@RequestBody UserDTO userDTO) {
		return ResponseEntity.ok()
						.body(userAdapter.toResponseBase(userService.update(userAdapter.toEntity(userDTO))));

	}
	@ApiOperation("Delete User")
	@DeleteMapping(REQUEST_MAPPING_BY_ID)
	public ResponseEntity<?> delete(@PathVariable String id) {
		return ResponseEntity.ok()
				.body(userAdapter.toResponseBase(userService.delete(id)));

	}
}