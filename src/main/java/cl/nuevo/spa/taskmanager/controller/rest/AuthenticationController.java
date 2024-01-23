package cl.nuevo.spa.taskmanager.controller.rest;

import cl.nuevo.spa.taskmanager.domain.common.ApiBaseExceptionDetail;
import cl.nuevo.spa.taskmanager.domain.dto.AuthenticationRequestDto;
import cl.nuevo.spa.taskmanager.domain.dto.UserDto;
import cl.nuevo.spa.taskmanager.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Login response entity.
   *
   * @param authenticationRequest the authentication request
   * @return the response entity
   */
  @Operation(summary = "Authenticate user", tags = "authentication")
  @ApiResponse(
      responseCode = "200",
      description = "Authentication successful",
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
  @ApiResponse(
      responseCode = "500",
      description = "Internal server error",
      content = {
        @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ApiBaseExceptionDetail.class))
      })
  @PostMapping(
      value = "/authenticate",
      consumes = "application/json",
      produces = "application/json")
  public ResponseEntity<UserDto> login(
      @RequestBody AuthenticationRequestDto authenticationRequest) {
    return ResponseEntity.ok(authenticationService.login(authenticationRequest));
  }
}
