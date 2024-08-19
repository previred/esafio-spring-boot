package cl.corellana.taskmanager.api;

import cl.corellana.taskmanager.api.model.SignUpRequest;
import cl.corellana.taskmanager.service.SignUpService;
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
@RequestMapping(value = "/sign-up")
@Validated
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    /**
     * POST /sign-up : Sign Up
     *
     * @param signUpRequest  (optional)
     * @return Created (status code 201)
     */
    @Operation(
            operationId = "postSignUp",
            summary = "Sign Up",
            tags = {  },
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = { "application/json" }
    )
    ResponseEntity<Void> postSignUp(
            @Parameter(name = "PostSignUpRequest", description = "") @Valid @RequestBody(required = false) SignUpRequest signUpRequest
    ){
        signUpService.signUp(signUpRequest);
        return ResponseEntity.status(201).body(null);
    }
}
