/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Interfaces;

import com.pe.sysventas.eas.Entidades.Producto;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Deary
 */
public interface IProducto extends CrudRepository<Producto,Long>{
    Producto findByIdProducto(Long idpro);
}
