/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Interfaces;

import com.pe.sysventas.eas.Entidades.*;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Deary
 */
public interface ICliente extends CrudRepository<Cliente,Long>{
    
}