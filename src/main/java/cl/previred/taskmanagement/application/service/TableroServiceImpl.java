package cl.previred.taskmanagement.application.service;

import cl.previred.taskmanagement.application.dto.request.CrearTableroRequest;
import cl.previred.taskmanagement.application.dto.response.EstadoRespuestaDTO;
import cl.previred.taskmanagement.application.dto.response.RespuestaTableroDTO;
import cl.previred.taskmanagement.application.dto.response.TableroDTO;
import cl.previred.taskmanagement.application.dto.response.TareaDTO;
import cl.previred.taskmanagement.application.mapper.TableroMapper;
import cl.previred.taskmanagement.application.mapper.TareaMapper;
import cl.previred.taskmanagement.core.domain.constant.CodigosEnum;
import cl.previred.taskmanagement.core.domain.entities.Tablero;
import cl.previred.taskmanagement.core.domain.entities.Tarea;
import cl.previred.taskmanagement.core.domain.exception.TableroDuplicadoException;
import cl.previred.taskmanagement.core.domain.exception.TableroNoEncontradoException;
import cl.previred.taskmanagement.core.domain.exception.TareaNoEncontradaException;
import cl.previred.taskmanagement.core.port.TableroRepository;
import cl.previred.taskmanagement.core.service.TableroService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TableroServiceImpl implements TableroService {

    private final TableroRepository tableroRepository;

    public TableroServiceImpl(TableroRepository tableroRepository){
        this.tableroRepository = tableroRepository;
    }

    @Override
    public RespuestaTableroDTO buscarTableroPorCodigo(String codigo){
        RespuestaTableroDTO respuestaTableroDTO = new RespuestaTableroDTO();

        Tablero tablero = tableroRepository.findByCodigo(codigo).orElseThrow(() -> new TableroNoEncontradoException("Tablero no encontrado"));
        respuestaTableroDTO.setInformacionTablero(TableroMapper.mapperTablero(tablero));


        if(tablero.getTareas() != null && !tablero.getTareas().isEmpty()){
            respuestaTableroDTO.setTareas(obtenerTareasTablero(tablero.getTareas()));
        }
        respuestaTableroDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.EXITO.getCode(), CodigosEnum.EXITO.getMessage()));
        return respuestaTableroDTO;

    }

    @Override
    public RespuestaTableroDTO crearTablero(CrearTableroRequest request) {

        RespuestaTableroDTO respuestaTableroDTO = new RespuestaTableroDTO();

        Optional<Tablero> tableroBuscar = tableroRepository.findByCodigo(request.getCodigo());
        if(tableroBuscar.isPresent()){
            throw new TableroDuplicadoException("Está intentando duplicar el tablero");
        }

        Tablero tablero = new Tablero();
        tablero.setCodigo(request.getCodigo());
        tablero.setNombre(request.getNombre());
        tablero.setDescripcion(request.getDescripcion());
        tablero.setFechaCreacion(new Date());

        tableroRepository.save(tablero);

        if(tablero.getTareas() != null && !tablero.getTareas().isEmpty()){
            respuestaTableroDTO.setTareas(obtenerTareasTablero(tablero.getTareas()));
        }

        respuestaTableroDTO.setInformacionTablero(TableroMapper.mapperTablero(tablero));
        respuestaTableroDTO.setEstado(new EstadoRespuestaDTO(CodigosEnum.EXITO.getCode(), CodigosEnum.EXITO.getMessage()));
        return respuestaTableroDTO;
    }

    List<TareaDTO> obtenerTareasTablero(List<Tarea> listaTareas){
        List<TareaDTO> listaDTO = new ArrayList<>();
        listaTareas.forEach(t -> {
            listaDTO.add(TareaMapper.mapperTarea(t, false));
        });
        return listaDTO;
    }
}
