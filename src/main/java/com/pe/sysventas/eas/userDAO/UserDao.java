/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.userDAO;

import com.pe.sysventas.eas.Entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pe.sysventas.eas.Interfaces.IUser;
import com.pe.sysventas.eas.Interfaces.IUserCrud;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deary
 */
@Service
public class UserDao implements IUserCrud{
    @Autowired
    IUser userdao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) userdao.findAll();
    }

    @Override
    @Transactional
    public boolean guardar(Usuario usuario) {
        boolean respuesta=false;
        userdao.save(usuario);
        respuesta=true;
        return respuesta;
    }

    @Override
    @Transactional
    public boolean eliminar(Usuario usuario) {
        boolean respuesta=false;
        userdao.delete(usuario);
        respuesta=true;
        return respuesta;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscar(Usuario usuario) {
        usuario = userdao.findById(usuario.getId()).orElse(null);
        return usuario;
    }
    
}
