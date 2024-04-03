package com.nuevospa.app.controllers;

import com.nuevospa.app.dtos.request.SignInRequestDto;
import com.nuevospa.app.dtos.response.SignInResponseDto;
import com.nuevospa.app.models.ResponseError;
import com.nuevospa.app.providers.JwtTokenProvider;
import com.nuevospa.app.services.AuthService;
import com.nuevospa.app.services.JwtTokenService;
import com.nuevospa.app.utils.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "api/v1/auth", produces = "application/json", consumes = "application/json")
@Tag(name = "Auth")
@SecurityRequirements()
@ApiResponse(
        responseCode = "400",
        description = "Bad Request.",
        content = @Content(
                schema = @Schema(implementation = ResponseError.class)
        )
)
public class AuthController {
    private final AuthService authService;
    private final JwtTokenService jwtTokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(AuthService authService, JwtTokenService jwtTokenService, JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.jwtTokenService = jwtTokenService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = "/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Sign in", description = "Allows a user to authenticate in the system.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully authenticated.",
                    content = @Content(
                            schema = @Schema(implementation = SignInResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Wrong credentials.",
                    content = @Content(
                            schema = @Schema(implementation = ResponseError.class)
                    )
            )
    })
    public SignInResponseDto authenticateUser(@RequestBody @Valid SignInRequestDto singInDTO) {
        Authentication authentication = authService.authenticate(singInDTO);
        String token = jwtTokenService.generateToken(authentication);
        Date expirationDate = jwtTokenProvider.getExpirationDateFromToken(token);
        String formattedExpirationDate = DateUtil.formatToChileanDate(expirationDate);
        return SignInResponseDto.builder()
                .accessToken(token)
                .expirationDate(formattedExpirationDate)
                .build();

    }
}

