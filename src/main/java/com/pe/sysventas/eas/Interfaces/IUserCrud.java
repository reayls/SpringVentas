/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Interfaces;

import com.pe.sysventas.eas.Entidades.Usuario;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Deary
 */
public interface IUserCrud {
    
    public List<Usuario> listar();
    
    public boolean guardar(Usuario usuario);
    
    public boolean eliminar(Usuario usuario);
    
    public Usuario buscar(Usuario usuario);
}
