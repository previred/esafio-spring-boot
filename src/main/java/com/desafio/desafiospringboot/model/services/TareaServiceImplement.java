package com.desafio.desafiospringboot.model.services;


import com.desafio.desafiospringboot.model.dao.EstadosTarea;
import com.desafio.desafiospringboot.model.dao.Tarea;
import com.desafio.desafiospringboot.model.exceptions.DeleteTaskException;
import com.desafio.desafiospringboot.model.exceptions.UpdateTaskException;
import com.desafio.desafiospringboot.model.repositories.EstadosTareaRepositorioJPA;
import com.desafio.desafiospringboot.model.repositories.TareaRepositorioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class TareaServiceImplement implements TareaInterface{

    @Autowired
    private TareaRepositorioJPA tareaRepositorioJPA;

    @Autowired
    private EstadosTareaRepositorioJPA estadosTareaRepositorioJPA;

    public TareaServiceImplement() {
    }

    @Transactional(readOnly = true)
    @Override
    public List<Tarea> mostrarTodasLasTareas(Long id) {

        return (List<Tarea>) tareaRepositorioJPA.listarTareas(id);
    }

    @Transactional
    @Override
    public Tarea crearTarea(Tarea tarea) {
        Long idEstado=estadosTareaRepositorioJPA.contarEstados();
        System.out.println("idEstado ========================== " + idEstado);
        System.out.println("tarea.getEstadosTarea().getId() ========================== " + tarea.getEstadosTarea().getId());
        if (tarea.getEstadosTarea().getId()==null){
            tarea.getEstadosTarea().setId(idEstado+1L);
        }
        System.out.println("tarea.getEstadosTarea().getId() ========================== " + tarea.getEstadosTarea().getId());
        Optional<EstadosTarea> estadosTarea =estadosTareaRepositorioJPA.buscarEstado(tarea.getEstadosTarea().getId());
        if (estadosTarea.isPresent()){
            tarea.setEstadosTarea(estadosTarea.orElseThrow());
            estadosTarea.orElseThrow().setTarea(tarea);
        }
        tarea.setEstadosTarea(tarea.getEstadosTarea());
        System.out.println("tarea.getEstadosTarea()) ========================== " + tarea.getEstadosTarea());
        return tareaRepositorioJPA.save(tarea);


    }

    @Transactional
    @Override
    public Optional<Tarea> actualizarTarea(Long id, Tarea tarea) {
        Optional<Tarea> tareaOptional= tareaRepositorioJPA.findById(id);
        if(tareaOptional.isPresent()){
            Tarea miTarea= tareaOptional.orElseThrow();
            Optional<EstadosTarea> estadosTarea =estadosTareaRepositorioJPA.buscarEstado(miTarea.getEstadosTarea().getId());
            if (estadosTarea.isPresent()){
                miTarea.setEstadosTarea(estadosTarea.orElseThrow());
                estadosTarea.orElseThrow().setTarea(tarea);
            }
            miTarea.setTitulo(tarea.getTitulo());
            miTarea.setUsuario(tarea.getUsuario());
            return Optional.of(tareaRepositorioJPA.save(miTarea));

        }else {
            throw new UpdateTaskException("Error al actualizar la Tarea");
        }
    }

    @Transactional
    @Override
    public Optional<Tarea> borrarTarea(Long id) {
        Optional<Tarea> tareaOptional=null;
        try{
            tareaOptional = tareaRepositorioJPA.findById(id);
            tareaOptional.ifPresent(tareaDB -> {
                tareaRepositorioJPA.delete(tareaDB);
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        /*if (tareaOptional.isEmpty()){
            throw new DeleteTaskException("Error al eliminar la Tarea");
        }*/
        return tareaOptional;
    }

}
