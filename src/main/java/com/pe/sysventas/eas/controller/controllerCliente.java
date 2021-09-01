/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pe.sysventas.eas.Entidades.Cliente;
import com.pe.sysventas.eas.Entidades.Views;
import com.pe.sysventas.eas.Interfaces.ICliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Deary
 */
@Controller
@RequestMapping("/clientes")
public class controllerCliente {
    
    @Autowired
    ICliente icliente;
    
    @GetMapping("")
    public String listaClientes(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes", icliente.findAll());
        return "view-clientes";
    }

    @PostMapping("/guardar")
    public String guardarCliente(Cliente cliente) {
        icliente.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    @JsonView(Views.Clientes.class)
    public ResponseEntity<Cliente> editarCliente(Cliente cliente) {

        return new ResponseEntity<Cliente>(icliente.findById(cliente.getId()).get(), HttpStatus.OK);
    }
    
    @GetMapping("/buscar/{id}")
    @JsonView(Views.Clientes.class)
    public ResponseEntity<Cliente> buscarCliente(Cliente cliente) {

        return new ResponseEntity<Cliente>(icliente.findById(cliente.getId()).orElse(null), HttpStatus.OK);
    }
    
    @GetMapping("/clients")
    @JsonView(Views.Clientes.class)
    public ResponseEntity<List<Cliente>> clientess(){
        
        return new ResponseEntity<List<Cliente>>(icliente.findAll(), HttpStatus.OK);
    }
}
