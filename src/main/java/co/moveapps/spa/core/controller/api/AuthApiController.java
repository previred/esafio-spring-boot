package co.moveapps.spa.core.controller.api;


import co.moveapps.spa.core.controller.model.CredentialsRequest;
import co.moveapps.spa.core.controller.model.CredentialsResponse;
import co.moveapps.spa.core.exception.BusinessException;
import co.moveapps.spa.core.service.auth.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-04T21:17:31.347645-05:00[America/Bogota]")
@Controller
@RequestMapping("${openapi.swaggerCoreNuevoSPAOpenAPI30.base-path:/api}")
public class AuthApiController implements AuthApi {

    private final NativeWebRequest request;
    private final AuthenticationService authenticationService;

    public AuthApiController(NativeWebRequest request, AuthenticationService authenticationService) {
        this.request = request;
        this.authenticationService = authenticationService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }


    @Override
    public ResponseEntity<CredentialsResponse> login(CredentialsRequest credentialsRequest) throws BusinessException {
        return ResponseEntity.ok(authenticationService.authentication(credentialsRequest.getEmail(), credentialsRequest.getPassword()));
    }
}
