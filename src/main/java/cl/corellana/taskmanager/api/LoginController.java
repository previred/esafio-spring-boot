package cl.corellana.taskmanager.api;

import cl.corellana.taskmanager.api.model.LoginRequest;
import cl.corellana.taskmanager.api.model.LoginResponse;
import cl.corellana.taskmanager.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
@Validated
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * POST /login : Login
     *
     * @param loginRequest  (optional)
     * @return  (status code 200)
     */
    @Operation(
            operationId = "postLogin",
            summary = "Login",
            tags = {  },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    ResponseEntity<LoginResponse> postLogin(
            @Parameter(name = "LoginRequest", description = "") @Valid @RequestBody(required = false) LoginRequest loginRequest
    ){
        return ResponseEntity.ok(loginService.authenticate(loginRequest));
    }
}
