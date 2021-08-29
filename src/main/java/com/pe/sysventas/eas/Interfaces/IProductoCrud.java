/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Interfaces;

import com.pe.sysventas.eas.Entidades.Producto;
import java.util.List;

/**
 *
 * @author Deary
 */
public interface IProductoCrud {
    
    public List<Producto> listar();
    
    public boolean guardar(Producto producto);
    
    public boolean eliminar(Producto producto);
    
    public Producto buscar(Producto producto);
}
