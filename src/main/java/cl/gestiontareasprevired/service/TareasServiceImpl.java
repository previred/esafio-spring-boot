package cl.gestiontareasprevired.service;

import cl.gestiontareasprevired.dto.TareaReqDto;
import cl.gestiontareasprevired.exception.ErrorIngresoException;
import cl.gestiontareasprevired.exception.TareaDuplicadaException;
import cl.gestiontareasprevired.exception.UsuarioNoEncontradoException;
import cl.gestiontareasprevired.model.EstadosTarea;
import cl.gestiontareasprevired.model.Tarea;
import cl.gestiontareasprevired.dto.TareaDto;
import cl.gestiontareasprevired.model.Usuarios;
import cl.gestiontareasprevired.repository.EstadosTareaRepository;
import cl.gestiontareasprevired.repository.TareasRepository;
import cl.gestiontareasprevired.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    TareasRepository tareasRepository;

    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    EstadosTareaRepository estadosTareaRepository;


    @Autowired
    ModelMapper modelMapper;



    @Override
    public List<TareaDto> getAllTareas(){
        List<Tarea> data = tareasRepository.findAll();
        return data.stream()
                .map(entidad -> modelMapper.map(entidad, TareaDto.class))
                .collect(Collectors.toList());
    }

    public List<TareaDto> getTareasPorMail(String email) {
        List<Tarea> data = tareasRepository.findByUsuario_Email(email);
        return data.stream()
                .map(entidad -> modelMapper.map(entidad, TareaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TareaDto> getTareaPorId(String id) {
        Optional<Tarea> tarea = tareasRepository.findById(id);
        return tarea.map(entidad -> modelMapper.map(entidad, TareaDto.class));
    }

    @Override
    public void eliminarTareaPorId(String idTarea) {
        tareasRepository.deleteById(idTarea);
    }

    @Override
    public String crearTarea(TareaReqDto tareaReqDto) {
        Usuarios usuario = usuariosRepository.findByEmail(tareaReqDto.getUsername())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        tareasRepository.findByTituloTareaAndUsuario_Email(tareaReqDto.getTituloTarea(), tareaReqDto.getUsername())
                .ifPresent(t -> {
                    throw new TareaDuplicadaException("El usuario ya tiene una tarea con el mismo título");
                });

        EstadosTarea estado = estadosTareaRepository.findById(tareaReqDto.getEstado())
                .orElseThrow(() -> new ErrorIngresoException("Estado de tarea no encontrado"));

        Tarea nuevaTarea = new Tarea();
        nuevaTarea.setIdTarea(UUID.randomUUID().toString());
        nuevaTarea.setTituloTarea(tareaReqDto.getTituloTarea());
        nuevaTarea.setDescripcionTarea(tareaReqDto.getDescripcionTarea());
        nuevaTarea.setUsuario(usuario);
        nuevaTarea.setFechaIngreso(LocalDateTime.now());
        nuevaTarea.setFechaFin(null);
        nuevaTarea.setEstado(estado);

        tareasRepository.save(nuevaTarea);

        return nuevaTarea.getIdTarea();
    }


    @Override
    public void actualizarTarea(String idTarea, TareaReqDto tareaReqDto) {
        Tarea tarea = tareasRepository.findById(idTarea)
                .orElseThrow(() -> new ErrorIngresoException("Tarea no encontrada"));

        tareasRepository.findByTituloTareaAndUsuario_Email(tareaReqDto.getTituloTarea(), tareaReqDto.getUsername())
                .ifPresent(t -> {
                    throw new TareaDuplicadaException("El usuario ya tiene una tarea con el mismo título");
                });

        Usuarios usuario = usuariosRepository.findByEmail(tareaReqDto.getUsername())
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        EstadosTarea estado = estadosTareaRepository.findById(tareaReqDto.getEstado())
                .orElseThrow(() -> new ErrorIngresoException("Estado de tarea no encontrado"));

        tarea.setTituloTarea(tareaReqDto.getTituloTarea());
        tarea.setDescripcionTarea(tareaReqDto.getDescripcionTarea());
        tarea.setFechaUltimaMod(LocalDateTime.now());
        tarea.setUsuario(usuario);
        tarea.setEstado(estado);
        if ("FIN".equals(tareaReqDto.getEstado())) {
            tarea.setFechaFin(LocalDateTime.now());
        }

        tareasRepository.save(tarea);
    }




}
