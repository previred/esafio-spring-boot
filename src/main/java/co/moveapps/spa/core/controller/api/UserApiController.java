package co.moveapps.spa.core.controller.api;


import co.moveapps.spa.core.controller.model.UserResponse;
import co.moveapps.spa.core.exception.BusinessException;
import co.moveapps.spa.core.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-04T21:17:31.347645-05:00[America/Bogota]")
@Controller
@RequestMapping("${openapi.swaggerCoreNuevoSPAOpenAPI30.base-path:/api}")
public class UserApiController implements UserApi {

    private final NativeWebRequest request;
    private final UserServiceImpl userService;

    @Autowired
    public UserApiController(NativeWebRequest request, UserServiceImpl userService) {
        this.request = request;
        this.userService = userService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers(Integer page, Integer limit) {
        return ResponseEntity.ok(userService.getAll(page, limit));
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(UUID id) throws BusinessException {
        return ResponseEntity.ok(userService.getById(id));
    }

    @Override
    public ResponseEntity<Boolean> deleteUser(UUID id) throws BusinessException {
        return ResponseEntity.ok(userService.delete(id));
    }
}
