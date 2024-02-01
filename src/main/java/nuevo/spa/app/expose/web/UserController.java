package nuevo.spa.app.expose.web;

import com.spring.nuevo.spa.app.model.UserResponse;
import lombok.RequiredArgsConstructor;
import nuevo.spa.app.business.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/spa/v1", produces = "application/json")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/security/user")
    public UserResponse login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        return UserResponse.builder().user(username).token(authenticationService.getJWTToken(username)).build();

    }

}
