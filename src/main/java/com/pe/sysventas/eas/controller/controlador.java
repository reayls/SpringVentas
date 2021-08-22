/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.controller;

import com.pe.sysventas.eas.Entidades.*;
import com.pe.sysventas.eas.Interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Deary
 */
@Controller
public class controlador {
    @Autowired
    IUserCrud user;
    @Autowired
    IClienteCrud cliente;
    
    @GetMapping("/")
    public String inicio(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/ingresar")
    public String ingresar(Model model){
        Usuario us=new Usuario();
        Cliente cli= new Cliente();
        us.setEmail("luis@corre.com");
        us.setPassword("luis");
        cli.setNombre("Luis");
        cli.setApellido("Quispe Lopez");
        cli.setDireccion("AQP");
        us.setCliente(cli);
        user.guardar(us);
        return "redirect:/";
    }
}
