package com.previred.model.dao;

import com.previred.model.entitys.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioDao extends CrudRepository<Usuario,Long> {

    Usuario findByMail(@Param(value = "mail") String mail);
    Usuario findByMailAndPassword(@Param(value = "mail") String mail,@Param(value = "password") String password);
}
