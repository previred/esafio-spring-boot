package cl.previred.nuevospa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.nuevospa.business.TareaBusiness;
import cl.previred.nuevospa.dto.ResponseApiDto;
import cl.previred.nuevospa.dto.TareaDto;
import cl.previred.nuevospa.exceptions.ElementoNoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tarea")
public class TareasController {

    @Autowired
    private TareaBusiness tareaBusiness;

    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal().toString();
    }

    @Operation(summary = "Obtener tareas del usuario", description = "Endpoint que devuelve todas las tareas del usuario al que pertenece el token JWT.", tags={ "Tareas" })
    @GetMapping
    public ResponseEntity<ResponseApiDto> getTareas() {
        try {
            List<TareaDto> respuesta = tareaBusiness.obtenerTareas(this.getUsername());
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.OK.getReasonPhrase(), respuesta), HttpStatus.OK
            );
        } catch (ElementoNoEncontradoException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Obtener una tarea del usuario", description = "Endpoint que devuelve la tarea del usuario al que pertenece el token JWT y el ID de tarea ingresado en la URL.", tags={ "Tareas" })
    @GetMapping("/{id}")
    public ResponseEntity<ResponseApiDto> getTarea(
        @Parameter(in = ParameterIn.PATH, description = "ID de la tarea a buscar", required=true, schema=@Schema())
        @PathVariable("id") 
        String idString
    ) {
        if(idString.isBlank()){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto("Campo id obligatorio", null), HttpStatus.BAD_REQUEST
            );
        }
        Long idLong = null;
        try{
            idLong = Long.parseLong(idString);
        }catch(NumberFormatException nfe){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(nfe.getMessage(), null), HttpStatus.BAD_REQUEST)
            ;
        }
        try {
            TareaDto respuesta = tareaBusiness.obtenerTareaPorId(this.getUsername(), idLong);
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.OK.getReasonPhrase(), respuesta), HttpStatus.OK
            );
        } catch (ElementoNoEncontradoException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Borrar una tarea del usuario", description = "Endpoint que borra la tarea según el ID de tarea indicado y del token JWT que se asocia al usuario.", tags={ "Tareas" })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApiDto> deleteTarea(
        @Parameter(in = ParameterIn.PATH, description = "ID de la tarea a eliminar", required=true, schema=@Schema())
        @PathVariable("id") 
        String idString
        ) {
        if(idString.isBlank()){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto("Campo id obligatorio", null), HttpStatus.BAD_REQUEST
            );
        }
        Long idLong = null;
        try{
            idLong = Long.parseLong(idString);
        }catch(NumberFormatException nfe){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(nfe.getMessage(), null), HttpStatus.BAD_REQUEST)
            ;
        }
        try {
            tareaBusiness.eliminarTareaPorId(this.getUsername(), idLong);
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.OK.getReasonPhrase(), null), HttpStatus.OK
            );
        } catch (ElementoNoEncontradoException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null), HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Crear una tarea del usuario", description = "Endpoint que crea una tarea nueva tarea asociada al usuario al que pertenece el token JWT.", tags={ "Tareas" })
    @PostMapping
    public ResponseEntity<ResponseApiDto> agregarTarea(@RequestBody TareaDto tareaDto) {
        try {
            tareaDto = tareaBusiness.agregarTarea(this.getUsername(), tareaDto);
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.OK.getReasonPhrase(), tareaDto), HttpStatus.CREATED
            );
        } catch (ElementoNoEncontradoException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null), HttpStatus.NOT_FOUND
            );
        } catch (IllegalArgumentException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Editar una tarea del usuario", description = "Endpoint que edita la tarea según el ID de tarea indicado y del token JWT que se asocia al usuario.", tags={ "Tareas" })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiDto> actualizarTarea(
        @Parameter(in = ParameterIn.PATH, description = "ID de la tarea a modificar", required=true, schema=@Schema())
        @PathVariable("id") 
        String idString, 
        @RequestBody 
        TareaDto tareaDto) {
        if(idString.isBlank()){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto("Campo id obligatorio", null), HttpStatus.BAD_REQUEST
            );
        }
        Long idLong = null;
        try{
            idLong = Long.parseLong(idString);
        }catch(NumberFormatException nfe){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(nfe.getMessage(), null), HttpStatus.BAD_REQUEST)
            ;
        }
        try {
            tareaDto = tareaBusiness.modificarTarea(this.getUsername(), idLong, tareaDto);
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.OK.getReasonPhrase(), tareaDto), HttpStatus.OK
            );
        } catch (ElementoNoEncontradoException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null), HttpStatus.NOT_FOUND
            );
        } catch (IllegalArgumentException e){
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(e.getMessage(), null), HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<ResponseApiDto>(
                new ResponseApiDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
