package com.nuevospa.taskcontrol.services.impl;

import com.nuevospa.taskcontrol.dtos.entities.EstadoTarea;
import com.nuevospa.taskcontrol.dtos.entities.HistorialEstado;
import com.nuevospa.taskcontrol.dtos.entities.Tarea;
import com.nuevospa.taskcontrol.dtos.entities.Usuario;
import com.nuevospa.taskcontrol.dtos.requests.AddTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.DeleteTareaRequest;
import com.nuevospa.taskcontrol.dtos.requests.UpdateTareaRequest;
import com.nuevospa.taskcontrol.dtos.responses.TareaResponse;
import com.nuevospa.taskcontrol.repositories.EstadoTareaRepository;
import com.nuevospa.taskcontrol.repositories.HistorialEstadoRepository;
import com.nuevospa.taskcontrol.repositories.TareaRepository;
import com.nuevospa.taskcontrol.repositories.UsuarioRepository;
import com.nuevospa.taskcontrol.services.TareaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TareaServiceImpl implements TareaService {

    final private TareaRepository tareaRepository;

    final private UsuarioRepository usuarioRepository;

    final private EstadoTareaRepository estadoTareaRepository;

    private static final Logger logger = LoggerFactory.getLogger(TareaServiceImpl.class);

    private static final long FIRST_ELEMENT_ID = 1;

    private static final String FINISHED_TASK_STATUS = "Terminada";

    @Autowired
    public TareaServiceImpl(TareaRepository tareaRepository,
                            UsuarioRepository usuarioRepository, EstadoTareaRepository estadoTareaRepository, HistorialEstadoRepository historialEstadoRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
        this.estadoTareaRepository = estadoTareaRepository;
    }

    @Override
    public TareaResponse addTarea(AddTareaRequest request) {
        TareaResponse response = null;
        try {
            Tarea tarea = new Tarea();
            tarea.setNombre(request.getNombre());
            tarea.setDescripcion(request.getDescripcion());
            Optional<Usuario> usuario = this.usuarioRepository.
                    findById(request.getIdUsuario());
            if (!usuario.isEmpty()) {
                tarea.setUsuario(usuario.get());
                usuario.get().addTarea(tarea);
            }
            HistorialEstado historialEstado = defineHistorialEstadoTarea(tarea);
            tarea.addHistorialEstado(historialEstado);
            Tarea tareaGuardada = this.tareaRepository.save(tarea);
            response = new TareaResponse();
            response.setId(tareaGuardada.getIdTarea());
            response.setNombre(tareaGuardada.getNombre());
            response.setDescripcion(tareaGuardada.getDescripcion());
            response.setUsuario(usuario.get());
            response.setHistorialEstados(tareaGuardada.getHistorialEstados());
        } catch (Exception e) {
            logger.error("An exception was produced: {}", e);
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    @Override
    public TareaResponse deleteTarea(DeleteTareaRequest request) {
        TareaResponse response = null;
        try {
            Optional<Tarea> tarea = this.tareaRepository.findById(request.getIdTarea());
            if (!tarea.isEmpty()) {
                response = new TareaResponse();
                response.setId(tarea.get().getIdTarea());
                response.setNombre(tarea.get().getNombre());
                response.setDescripcion(tarea.get().getDescripcion());
                response.setUsuario(tarea.get().getUsuario());
                response.setHistorialEstados(tarea.get().getHistorialEstados());
                this.tareaRepository.delete(tarea.get());
            }
        } catch (Exception e) {
            logger.error("An exception was produced: {}", e);
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    @Override
    public TareaResponse readTarea(long idTarea) {
        TareaResponse response = null;
        try {
            Optional<Tarea> tarea = this.tareaRepository.findById(idTarea);
            if (!tarea.isEmpty()) {
                response = new TareaResponse();
                response.setId(tarea.get().getIdTarea());
                response.setNombre(tarea.get().getNombre());
                response.setDescripcion(tarea.get().getDescripcion());
                response.setUsuario(tarea.get().getUsuario());
                response.setHistorialEstados(tarea.get().getHistorialEstados());
            }
        } catch (Exception e) {
            logger.error("An exception was produced: {}", e);
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    @Override
    public TareaResponse updateTarea(UpdateTareaRequest request) {
        TareaResponse response = null;
        try {
            Optional<Tarea> tarea = this.tareaRepository.findById(request.getIdTarea());
            Optional<Usuario> usuario = this.usuarioRepository.findById(request.getIdUsuario());
            if (!tarea.isEmpty() && !usuario.isEmpty()) {
                tarea.get().setUsuario(usuario.get());
            }
            Tarea tareaActualizada = null;
            tareaActualizada = updateHistoralEstadoTarea(request, tarea, tareaActualizada);
            if (tareaActualizada != null) {
                response = new TareaResponse();
                response.setId(tareaActualizada.getIdTarea());
                response.setNombre(tareaActualizada.getNombre());
                response.setDescripcion(tareaActualizada.getDescripcion());
                response.setUsuario(tareaActualizada.getUsuario());
                response.setHistorialEstados(tareaActualizada.getHistorialEstados());
            }
        } catch (Exception e) {
            logger.error("An exception was produced: {}", e);
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }

    private Tarea updateHistoralEstadoTarea(UpdateTareaRequest request, Optional<Tarea> tarea, Tarea tareaActualizada) {
        Optional<EstadoTarea> nuevoEstado = this.estadoTareaRepository.findById(request.getIdEstadoTarea());
        if (!tarea.isEmpty() && !nuevoEstado.isEmpty()) {
            Optional<HistorialEstado> historialEstadoAnterior = tarea.get().getHistorialEstados().stream()
                    .filter(historialEstado -> historialEstado.getFechaFinEstado() == null)
                    .findFirst();
            historialEstadoAnterior.get().setFechaFinEstado(LocalDateTime.now());
            HistorialEstado nuevoHistorialEstado = new HistorialEstado();
            nuevoHistorialEstado.setTarea(tarea.get());
            nuevoHistorialEstado.setEstadoTarea(nuevoEstado.get());
            nuevoHistorialEstado.setFechaInicioEstado(LocalDateTime.now());
            if(FINISHED_TASK_STATUS.equals(nuevoEstado.get().getEstado())) {
                nuevoHistorialEstado.setFechaFinEstado(LocalDateTime.now());
            }
            tarea.get().addHistorialEstado(nuevoHistorialEstado);
            tareaActualizada = tareaRepository.save(tarea.get());
        }
        return tareaActualizada;
    }

    private HistorialEstado defineHistorialEstadoTarea(Tarea tarea) {
        Optional<EstadoTarea> estadoTarea = this.estadoTareaRepository.
                findById(FIRST_ELEMENT_ID);
        HistorialEstado historialEstado = null;
        if (tarea != null && !estadoTarea.isEmpty()) {
            historialEstado = new HistorialEstado();
            historialEstado.setTarea(tarea);
            historialEstado.setEstadoTarea(estadoTarea.get());
            historialEstado.setFechaInicioEstado(LocalDateTime.now());
        }
        return historialEstado;
    }
}
