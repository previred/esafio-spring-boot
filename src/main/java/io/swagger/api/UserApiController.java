package io.swagger.api;

import io.swagger.model.TokenResponse;
import io.swagger.service.AuthService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-07-03T02:16:23.989331700Z[GMT]")
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController implements UserApi {

    private final AuthService authService;

    public ResponseEntity<TokenResponse> loginUser(
            @Parameter(in = ParameterIn.QUERY, description = "The user name for login", schema = @Schema())
            @Valid @RequestParam(value = "username", required = false) String username
            , @Parameter(in = ParameterIn.QUERY, description = "The password for login in clear text", schema = @Schema())
            @Valid @RequestParam(value = "password", required = false) String password
    ) {
        TokenResponse tokenResponse = authService.authenticate(username, password);

        return ResponseEntity.ok(tokenResponse);
    }

}
