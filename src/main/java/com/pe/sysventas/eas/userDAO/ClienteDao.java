/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.userDAO;

import com.pe.sysventas.eas.Entidades.*;
import com.pe.sysventas.eas.Interfaces.ICliente;
import com.pe.sysventas.eas.Interfaces.IClienteCrud;
import com.pe.sysventas.eas.Interfaces.IUserCrud;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deary
 */
@Service
public class ClienteDao implements IClienteCrud{
    @Autowired
    ICliente clientdao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return (List<Cliente>) clientdao.findAll();
    }

    @Override
    @Transactional
    public boolean guardar(Cliente cliente) {
        boolean respuesta=false;
        clientdao.save(cliente);
        respuesta=true;
        return respuesta;
    }

    @Override
    @Transactional
    public boolean eliminar(Cliente cliente) {
        boolean respuesta=false;
        clientdao.delete(cliente);
        respuesta=true;
        return respuesta;
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscar(Cliente cliente) {
        cliente = clientdao.findById(cliente.getId()).orElse(null);
        return cliente;
    }

    
}
