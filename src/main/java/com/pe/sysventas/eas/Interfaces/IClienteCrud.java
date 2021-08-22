/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Interfaces;

import com.pe.sysventas.eas.Entidades.Cliente;
import java.util.List;

/**
 *
 * @author Deary
 */
public interface IClienteCrud {
    
    public List<Cliente> listar();
    
    public boolean guardar(Cliente cliente);
    
    public boolean eliminar(Cliente cliente);
    
    public Cliente buscar(Cliente cliente);
}
