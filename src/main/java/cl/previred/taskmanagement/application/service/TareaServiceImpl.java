package cl.previred.taskmanagement.application.service;



import cl.previred.taskmanagement.application.dto.request.ActualizarTareaRequest;
import cl.previred.taskmanagement.application.dto.request.CrearTareaRequest;
import cl.previred.taskmanagement.application.dto.response.EstadoRespuestaDTO;
import cl.previred.taskmanagement.application.dto.response.RespuestaDTO;
import cl.previred.taskmanagement.application.dto.response.TareaDTO;
import cl.previred.taskmanagement.application.mapper.TareaMapper;
import cl.previred.taskmanagement.core.domain.entities.Tablero;
import cl.previred.taskmanagement.core.domain.entities.Tarea;
import cl.previred.taskmanagement.core.domain.entities.Usuario;
import cl.previred.taskmanagement.core.domain.constant.CodigosEnum;
import cl.previred.taskmanagement.core.domain.exception.TableroNoEncontradoException;
import cl.previred.taskmanagement.core.domain.exception.TareaNoEncontradaException;
import cl.previred.taskmanagement.core.domain.exception.UsuarioNoEncontradoException;
import cl.previred.taskmanagement.core.port.TableroRepository;
import cl.previred.taskmanagement.core.port.TareaRepository;
import cl.previred.taskmanagement.core.port.UsuarioRepository;
import cl.previred.taskmanagement.core.service.TareaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {
    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TableroRepository tableroRepository;

    public TareaServiceImpl(TareaRepository tareaRepository, UsuarioRepository usuarioRepository, TableroRepository tableroRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
        this.tableroRepository = tableroRepository;
    }

    @Override
    public RespuestaDTO crear(CrearTareaRequest tareaRequest) {

        RespuestaDTO respuestaDTO = new RespuestaDTO();
        Tarea tareaEntity = new Tarea();
        if(tareaRequest.getUsuario() != null){
            Usuario usuarioEntity = usuarioRepository.findByUsuario(tareaRequest.getUsuario()).orElseThrow(() -> new UsuarioNoEncontradoException(tareaRequest.getUsuario()));
            tareaEntity.setUsuario(usuarioEntity);
            tareaEntity.setFechaAsignacion(new Date());
        }

        Tablero tablero = tableroRepository.findByCodigo(tareaRequest.getCodigoTablero()).orElseThrow(() -> new TableroNoEncontradoException("Tablero no encontrado"));

        tareaEntity.setTablero(tablero);
        tareaEntity.setFechaCreacion(new Date());
        tareaEntity.setEstado("TO_DO");
        tareaEntity.setNombre(tareaRequest.getNombre());
        tareaEntity.setDescripcion(tareaRequest.getDescripcion());

        tareaRepository.save(tareaEntity);
        respuestaDTO.setInformacionTarea(TareaMapper.mapperTarea(tareaEntity, true));
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.EXITO.getCode(), CodigosEnum.EXITO.getMessage()));
        respuestaDTO.setTareas(null);
        return respuestaDTO;


    }

    @Override
    public RespuestaDTO obtenerTodas() {
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        List<Tarea> listaTareas = tareaRepository.findAll();
        if(listaTareas != null && !listaTareas.isEmpty()){
            List<TareaDTO> listaDTO = new ArrayList<>();
            listaTareas.stream().forEach(t -> {
                listaDTO.add(TareaMapper.mapperTarea(t, true));
            });
            respuestaDTO.setTareas(listaDTO);
            respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.EXITO.getCode(), CodigosEnum.EXITO.getMessage()));
            return respuestaDTO;
        }
        else {
            throw new TareaNoEncontradaException("No se encuentran tareas");
        }
    }

    @Override
    public RespuestaDTO obtenerTareasUsuario(String usuario){
        RespuestaDTO respuestaDTO = new RespuestaDTO();

        Optional<Usuario> optUsuario = usuarioRepository.findByUsuario(usuario);
        if (optUsuario.isEmpty()) {
            throw new UsuarioNoEncontradoException(usuario);
        }
        List<Tarea> listaTareas = tareaRepository.findByUsuario(optUsuario.get());
        if(listaTareas != null && !listaTareas.isEmpty()){
            List<TareaDTO> listaDTO = new ArrayList<>();
            listaTareas.stream().forEach(t -> {
                listaDTO.add(TareaMapper.mapperTarea(t, true));
            });
            respuestaDTO.setTareas(listaDTO);
            respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.EXITO.getCode(), CodigosEnum.EXITO.getMessage()));
            return respuestaDTO;
        }
        else {
            throw new TareaNoEncontradaException(CodigosEnum.NO_SE_ENCUENTRAN_TAREAS.getMessage());
        }
    }

    @Override
    public RespuestaDTO actualizarTarea(Long codigo, ActualizarTareaRequest actualizarTareaRequest) {
        Tarea tareaEncontrada = tareaRepository.findByCodigo(codigo).orElseThrow(() -> new TareaNoEncontradaException("Tarea no encontrada"));
        tareaEncontrada.setNombre(actualizarTareaRequest.getNombre());
        tareaEncontrada.setDescripcion(actualizarTareaRequest.getDescripcion());

        if(actualizarTareaRequest.getEstado() != null){
            ActualizarTareaRequest.Estado estado = ActualizarTareaRequest.Estado.fromValue(actualizarTareaRequest.getEstado().value());
        }

        tareaEncontrada.setEstado(actualizarTareaRequest.getEstado().value());

        if(actualizarTareaRequest.getUsuario() != null && !actualizarTareaRequest.getUsuario().isEmpty()){
            Usuario usuarioEntity = usuarioRepository.findByUsuario(actualizarTareaRequest.getUsuario()).orElseThrow(() -> new UsuarioNoEncontradoException(actualizarTareaRequest.getUsuario()));
            tareaEncontrada.setUsuario(usuarioEntity);
            tareaEncontrada.setFechaAsignacion(new Date());
        }

        if(actualizarTareaRequest.getCodigoTablero() != null && !actualizarTareaRequest.getCodigoTablero().isEmpty()){
            Tablero tablero = tableroRepository.findByCodigo(actualizarTareaRequest.getCodigoTablero()).orElseThrow(() -> new TableroNoEncontradoException("Tablero no encontrado"));
            tareaEncontrada.setTablero(tablero);
        }


        tareaEncontrada.setFechaTermino(actualizarTareaRequest.getFechaTermino());
        tareaRepository.save(tareaEncontrada);

        RespuestaDTO respuestaDTO = new RespuestaDTO();
        respuestaDTO.setTareas(null);
        respuestaDTO.setInformacionTarea(TareaMapper.mapperTarea(tareaEncontrada, true));
        respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.EXITO.getCode(), CodigosEnum.EXITO.getMessage()));
        return respuestaDTO;

    }

    @Override
    public RespuestaDTO eliminarTarea(Long codigo){
        RespuestaDTO respuestaDTO = new RespuestaDTO();
        try {
            if (tareaRepository.findByCodigo(codigo).isEmpty()) {
                throw new TareaNoEncontradaException("La tarea que se intenta eliminar no existe");
            }
            tareaRepository.deleteByCodigo(codigo);
            respuestaDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.EXITO.getCode(), CodigosEnum.EXITO.getMessage()));
            return  respuestaDTO;
        } catch (TareaNoEncontradaException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Error al eliminar la tarea", ex);
        }


    }

}
