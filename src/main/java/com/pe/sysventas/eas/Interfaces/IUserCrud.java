/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Interfaces;

import com.pe.sysventas.eas.Entidades.*;
import java.util.List;
/**
 *
 * @author Deary
 */
public interface IUserCrud {
    
    public List<Usuario> listar();
    
    public boolean guardar(Usuario usuario);
    
    public boolean eliminar(Usuario usuario);
    
    public Usuario buscar(Usuario usuario);
    
    public Rol buscarRol(Rol rol);
    
    public Rol buscarRolId(int rolid);
}
