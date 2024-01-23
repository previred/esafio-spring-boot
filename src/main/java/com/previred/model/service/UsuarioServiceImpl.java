package com.previred.model.service;

import com.previred.model.dao.UsuarioDao;
import com.previred.model.entitys.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    private UsuarioDao usuarioDao;
    @Override
    public Usuario verificarUsuario(String mail,String password) {
        try {
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            return usuarioDao.findByMailAndPassword(mail,new BigInteger(1, messageDigest.digest()).toString(16));
        } catch (Exception e) {
            return null;
        }
    }
}
