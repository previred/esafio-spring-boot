package cl.previred.nuevospa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.nuevospa.business.UsuarioBusiness;
import cl.previred.nuevospa.dto.ResponseApiDto;
import cl.previred.nuevospa.dto.UsuarioDto;
import cl.previred.nuevospa.exceptions.ElementoNoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioBusiness userBusiness;

    @Operation(summary = "Obtener usuarios de la API", description = "Endpoint que devuelve todos los usuarios registrados en la BD.", tags={ "Usuarios" })
    @GetMapping()
    public ResponseEntity<ResponseApiDto> getUsuarios() {
        try {
            List<UsuarioDto> respuesta = userBusiness.obtenerUsuarios();
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.OK.getReasonPhrase(), respuesta),
                HttpStatus.OK
            );
        } catch (ElementoNoEncontradoException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null),
                HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Obtener usuario de la API por ID", description = "Endpoint que devuelve un usuario registrado en la BD según el ID de usuario indicado en la URL.", tags={ "Usuarios" })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseApiDto> getUsuario(@PathVariable("id") String idString) {
        if(idString.isBlank()){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto("Campo id obligatorio", null),
                HttpStatus.BAD_REQUEST
            );
        }
        Integer idInteger = null;
        try{
            idInteger = Integer.parseInt(idString);
        }catch(NumberFormatException nfe){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(nfe.getMessage(), null),
                HttpStatus.BAD_REQUEST)
            ;
        }
        try {
            UsuarioDto respuesta = userBusiness.obtenerUsuarioPorId(idInteger);
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.OK.getReasonPhrase(), respuesta),
                HttpStatus.OK
            );
        } catch (ElementoNoEncontradoException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null),
                HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
