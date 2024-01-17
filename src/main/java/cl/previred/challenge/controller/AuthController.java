package cl.previred.challenge.controller;

import cl.previred.challenge.config.security.JwtManager;
import cl.previred.challenge.config.security.SecureUserDetails;
import cl.previred.challenge.controller.handler.ResponseWrapper;
import cl.previred.challenge.controller.handler.ResponseHandler;
import cl.previred.challenge.dto.request.AuthRequest;
import cl.previred.challenge.dto.request.AuthResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.function.Supplier;

@RestController
@RequestMapping("/auth")
@Api(tags = "Autenticacion")
@ApiOperation(value = "Autenticacion Gestor Tareas")
public class AuthController extends ResponseHandler {

    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtManager jwtManager) {
        this.authenticationManager = authenticationManager;
        this.jwtManager = jwtManager;
    }

    @ApiOperation(
            value = "Autenticacion",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            response = AuthResponse.class)
    @PostMapping("/")
    public ResponseEntity<ResponseWrapper<AuthResponse>> auth(@RequestBody AuthRequest authRequest) {

        return wrapResponse(performAuthentication(authRequest));
    }

    private Supplier<AuthResponse> performAuthentication(AuthRequest authRequest) {
        return () -> {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

            if (Objects.nonNull(authenticate)) {
                SecureUserDetails userDetails = (SecureUserDetails) authenticate.getPrincipal();
                return new AuthResponse(jwtManager.generateToken(userDetails));
            }
            throw new BadCredentialsException("");
        };
    }
}
