package cl.nuevo.spa.taskmanager.controller.rest;

import cl.nuevo.spa.taskmanager.controller.validator.ValidUserRegistry;
import cl.nuevo.spa.taskmanager.domain.common.ApiBaseExceptionDetail;
import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.domain.dto.UserRegistryDto;
import cl.nuevo.spa.taskmanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@EnableMethodSecurity
@Validated
public class UserController {

  private final UserService userService;

  /**
   * Retrieve all users.
   *
   * @return List of UserDto
   */
  @Operation(summary = "Get all users", tags = "users")
  @ApiResponse(
      responseCode = "200",
      description = "Successful operation",
      content = {
        @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
      })
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('READ_ALL_USER')")
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  /**
   * Retrieve a user by ID.
   *
   * @param userId User ID
   * @return UserDto
   */
  @Operation(summary = "Get a user by ID", tags = "users")
  @ApiResponse(
      responseCode = "200",
      description = "Successful operation",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
      })
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "404",
      description = "User not found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('READ_ONE_USER')")
  @GetMapping("/{userId}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
    UserDto user = userService.getUserById(userId);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  /**
   * Create a new user.
   *
   * @param user UserRegistryDto for creating a new user
   * @return Created UserDto
   */
  @Operation(summary = "Create a new user", tags = "users")
  @ApiResponse(
      responseCode = "201",
      description = "User created successfully",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
      })
  @ApiResponse(
      responseCode = "400",
      description = "Invalid user details",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('CREATE_ONE_USER')")
  @PostMapping
  public ResponseEntity<UserDto> createUser(@RequestBody @ValidUserRegistry UserRegistryDto user) {
    return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
  }

  /**
   * Update an existing user.
   *
   * @param user Updated UserDto
   * @return Updated UserDto
   */
  @Operation(summary = "Update an existing user", tags = "users")
  @ApiResponse(
      responseCode = "200",
      description = "User updated successfully",
      content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
      })
  @ApiResponse(
      responseCode = "404",
      description = "User not found",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @SecurityRequirement(name = "Bearer Authentication")
  @PutMapping
  @PreAuthorize("hasAuthority('UPDATE_ONE_USER')")
  public ResponseEntity<UserDto> updateUser(
      @Parameter(description = "Updated user details", required = true) @RequestBody UserDto user) {
    UserDto updatedUser = userService.updateUser(user);
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  /**
   * Delete a user by ID.
   *
   * @param userId User ID to be deleted
   * @return No content response
   */
  @Operation(summary = "Delete a user by ID", tags = "users")
  @ApiResponse(responseCode = "204", description = "User deleted successfully")
  @ApiResponse(responseCode = "401", description = "Unauthorized")
  @ApiResponse(responseCode = "403", description = "Forbidden")
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasAuthority('DELETE_ONE_USER')")
  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
