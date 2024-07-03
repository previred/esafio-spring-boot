package ar.com.challenge.desafio_spring_boot.controller;

import ar.com.challenge.desafio_spring_boot.exception.ResourceFoundException;
import ar.com.challenge.desafio_spring_boot.exception.ResourceNotFoundException;
import ar.com.challenge.desafio_spring_boot.request.SigninRequest;
import ar.com.challenge.desafio_spring_boot.request.SignupRequest;
import ar.com.challenge.desafio_spring_boot.response.AuthResponse;
import ar.com.challenge.desafio_spring_boot.response.Response;
import ar.com.challenge.desafio_spring_boot.response.Error;
import ar.com.challenge.desafio_spring_boot.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication API")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Operation(
            summary = "singin",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(schema = @Schema(example = "{\"signinDto\": {\"username\":\"aariel\",\"password\":\"1234\"}}"))))
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = AuthResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @PostMapping("signin")
    public Response<AuthResponse, Error> signin(@RequestBody SigninRequest request) throws ResourceNotFoundException {

        var token = userService.loadUser(request.getSigninDto());

        var response = AuthResponse.builder().token(token).build();

        return Response.ok(response);
    }

    @Operation(
            summary = "signup",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(
                    example = "{ \"signupDto\": {\"name\":\"Ariel\",\"lastname\":\"Acho\",\"username\":\"AAcho\",\"password\":\"1234\",\"email\":\"arielandresacho@gmail.com\",\"role\":\"ADMIN\"}}")))
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = AuthResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "500", content = {@Content(
                    schema = @Schema(implementation = Error.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE)})})
    @PostMapping("signup")
    public Response<AuthResponse, Error> signup(@RequestBody SignupRequest request) throws ResourceNotFoundException, ResourceFoundException {

        var password = passwordEncoder.encode(request.getSignupDto().getPassword());

        request.getSignupDto().setPassword(password);

        var token = userService.createUser(request.getSignupDto());

        var response = AuthResponse.builder().token(token).build();

        return Response.ok(response);
    }
}
