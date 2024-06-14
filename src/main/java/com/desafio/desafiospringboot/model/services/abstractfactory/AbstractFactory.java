package com.desafio.desafiospringboot.model.services.abstractfactory;

import com.desafio.desafiospringboot.model.services.TareaInterface;
import com.desafio.desafiospringboot.model.services.TareaServiceImplement;
import com.desafio.desafiospringboot.model.services.UserInterface;
import com.desafio.desafiospringboot.model.services.UsuarioServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractFactory {
    @Autowired
    private UsuarioServiceImplement usuarioServiceImplement;
    @Autowired
    private TareaServiceImplement tareaServiceImplement;


    public UserInterface crearUsuario(){
            try {
                return usuarioServiceImplement;
            } catch (NullPointerException e){
                e.printStackTrace();
            }

        return null;
    }
    public TareaInterface crearTarea(){
        try {
            return tareaServiceImplement;
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        return null;
    }


}
