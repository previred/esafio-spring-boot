package cl.dpichinil.desafio.desafiospringboot.controller;

import cl.dpichinil.desafio.desafiospringboot.dto.ResponseDto;
import cl.dpichinil.desafio.desafiospringboot.dto.TareaDto;
import cl.dpichinil.desafio.desafiospringboot.service.TareaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tarea")
public class TareaController {
    private final TareaService tareaService;
    @GetMapping("list/estado")
    public ResponseEntity<ResponseDto> getAllEstadoTareaActive(){
        return tareaService.getAllEstadoTarea();
    }
    @GetMapping("list")
    public ResponseEntity<ResponseDto> getAllTareasActivas(){
        return tareaService.getAll();
    }
    @PostMapping({"","/"})
    public ResponseEntity<ResponseDto> create(@RequestBody TareaDto dto){
        return tareaService.create(dto);
    }
    @PutMapping({"","/"})
    public ResponseEntity<ResponseDto> update(@RequestBody TareaDto dto){
        return tareaService.update(dto);
    }
    @DeleteMapping({"/{id}"})
    public ResponseEntity<ResponseDto> delete(@PathVariable int id){
        return tareaService.delete(id);
    }
}
