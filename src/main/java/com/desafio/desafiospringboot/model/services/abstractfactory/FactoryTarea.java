package com.desafio.desafiospringboot.model.services.abstractfactory;

import com.desafio.desafiospringboot.model.dao.Tarea;
import com.desafio.desafiospringboot.model.services.TareaServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryTarea {
    @Autowired
    private AbstractFactory abstractFactory;
    public FactoryTarea() {

    }

    public List<Tarea> mostrarTodasLasTareas(Long id){
        TareaServiceImplement tareaServiceImplement=(TareaServiceImplement) abstractFactory.crearTarea();
        List<Tarea> misTareas= tareaServiceImplement.mostrarTodasLasTareas(id);
        return misTareas;
    }

    public Tarea crearTarea(Tarea tarea){
        TareaServiceImplement tareaServiceImplement=(TareaServiceImplement) abstractFactory.crearTarea();
        Tarea miTarea= tareaServiceImplement.crearTarea(tarea);
        return miTarea;
    }

    public Tarea actualizarTarea(Long id, Tarea tarea){
        TareaServiceImplement tareaServiceImplement=(TareaServiceImplement) abstractFactory.crearTarea();
        Tarea miTarea= tareaServiceImplement.actualizarTarea(id,tarea).get();
        return miTarea;
    }

    public Tarea borrarTarea(Long id){
        TareaServiceImplement tareaServiceImplement=(TareaServiceImplement) abstractFactory.crearTarea();
        Tarea miTarea= tareaServiceImplement.borrarTarea(id).get();
        return miTarea;
    }





}
