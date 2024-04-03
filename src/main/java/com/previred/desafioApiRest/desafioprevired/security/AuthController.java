package com.previred.desafioApiRest.desafioprevired.security;

import com.previred.desafioApiRest.desafioprevired.repository.model.Tarea;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    /**
     * @param request objeto de tipo LoginRequest
     * @return objeto de tipo ResponseEntity<AuthResponse> -- token generado tipo string en caso se que el login este OK
     */
    @ApiOperation(value = "Login de seguridad", notes = "Login de seguridad", response = Tarea.class, responseContainer = "Object")
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

}
