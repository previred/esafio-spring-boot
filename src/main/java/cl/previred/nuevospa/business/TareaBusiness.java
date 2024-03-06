package cl.previred.nuevospa.business;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.previred.nuevospa.business.mappers.TareaMapper;
import cl.previred.nuevospa.dto.TareaDto;
import cl.previred.nuevospa.entities.EstadoTarea;
import cl.previred.nuevospa.entities.Tarea;
import cl.previred.nuevospa.entities.Usuario;
import cl.previred.nuevospa.exceptions.ElementoNoEncontradoException;
import cl.previred.nuevospa.repository.TareaRepository;
import cl.previred.nuevospa.repository.UsuarioRepository;

@Service
public class TareaBusiness {
    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Validator validator;

    public TareaDto obtenerTareaPorId(String username, Long idTarea) throws ElementoNoEncontradoException {
        Tarea tareaEncontrada = tareaRepository.findById(username, idTarea);
        if(tareaEncontrada == null){
            throw new ElementoNoEncontradoException(Tarea.class, idTarea.toString());
        }
        return TareaMapper.tareaToTareaDto(tareaEncontrada);
    }

    public List<TareaDto> obtenerTareas(String username) throws ElementoNoEncontradoException{
        List<Tarea> tareasEncontradas = tareaRepository.findAll(username);
        if(tareasEncontradas.isEmpty()){
            throw new ElementoNoEncontradoException(Tarea.class);
        }

        List<TareaDto> tareasDto = tareasEncontradas.stream()
                .map(tarea -> TareaMapper.tareaToTareaDto(tarea))
                .collect(Collectors.toList());
        return tareasDto;
    }

    public void eliminarTareaPorId(String username, Long idTarea) throws ElementoNoEncontradoException {
        Tarea tareaEncontrada = tareaRepository.findById(username, idTarea);
        if(tareaEncontrada == null){
            throw new ElementoNoEncontradoException(Tarea.class, idTarea.toString());
        }
        tareaRepository.delete(tareaEncontrada);
    }

    public TareaDto agregarTarea(String username, TareaDto tareaDto) throws ElementoNoEncontradoException {
        Set<ConstraintViolation<TareaDto>> violations = validator.validate(tareaDto);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
            throw new IllegalArgumentException("Faltan campos obligatorios: \n "+errorMessage);
        }

        Usuario usuarioEncotrado = usuarioRepository.findByUsername(username);
        if(usuarioEncotrado == null){
            throw new ElementoNoEncontradoException(Usuario.class, username);
        }

        Tarea tareaNueva = new Tarea();
        tareaNueva.setTitulo(tareaDto.getTitulo());
        tareaNueva.setDescripcion(tareaDto.getDescripcion());
        tareaNueva.setFechaCreacion(new Date());
        tareaNueva.setEstado(new EstadoTarea(EstadoTarea.ESTADOS.PENDIENTE.getValue(), null));
        tareaNueva.setUsuario(usuarioEncotrado);
        
        tareaRepository.save(tareaNueva);

        return TareaMapper.tareaToTareaDto(tareaNueva);
    }

    public TareaDto modificarTarea(String username, Long idTarea, TareaDto tareaDto) throws ElementoNoEncontradoException {

        Set<ConstraintViolation<TareaDto>> violations = validator.validate(tareaDto);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));
            throw new IllegalArgumentException("Faltan campos obligatorios: \n "+errorMessage);
        }

        Usuario usuarioEncotrado = usuarioRepository.findByUsername(username);
        if(usuarioEncotrado == null){
            throw new ElementoNoEncontradoException(Usuario.class, username);
        }

        Tarea tareaEncontrada = tareaRepository.findById(username, idTarea);
        if(tareaEncontrada == null){
            throw new ElementoNoEncontradoException(Tarea.class, idTarea.toString());
        }

        tareaEncontrada.setTitulo(tareaDto.getTitulo());
        tareaEncontrada.setDescripcion(tareaDto.getDescripcion());
        tareaEncontrada.setFechaActualizacion(new Date());
        tareaEncontrada.setEstado(new EstadoTarea(tareaDto.getEstado().getId(), null));

        tareaRepository.update(tareaEncontrada);

        return TareaMapper.tareaToTareaDto(tareaEncontrada);
    }

    
    
}
