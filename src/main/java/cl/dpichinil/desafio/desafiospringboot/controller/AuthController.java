package cl.dpichinil.desafio.desafiospringboot.controller;

import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.UserDto;
import cl.dpichinil.desafio.desafiospringboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;
    @PostMapping("login")
    public ResponseEntity<ResponseDto> login(@RequestBody UserDto dto){
        return userService.login(dto);
    }
    @PostMapping("encodePassword")
    public ResponseEntity<ResponseDto> encodePassword(@RequestBody UserDto dto){
        return userService.encodePassword(dto);
    }

    @GetMapping("logged")
//    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public ResponseEntity<ResponseDto> logged(HttpServletRequest request){
        return userService.logged(request);
    }
}
