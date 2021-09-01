/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.controller;

import com.pe.sysventas.eas.Entidades.*;
import com.pe.sysventas.eas.Interfaces.*;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Deary
 */
@Controller
public class controlador {

    @Autowired
    IUserCrud usercrud;
    @Autowired
    IVentas iventa;
    @Autowired
    IUser iuser;
    @Autowired
    IProducto iproducto;
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping("/login")
    public String logse() {
        return "login";
    }

    @GetMapping("/signup")
    public String registrar(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "signup";
    }

    @GetMapping("/home")
    public String home(@AuthenticationPrincipal User user) {

        for (GrantedAuthority ce : user.getAuthorities()) {
            System.out.println("rol :" + ce.getAuthority());
        }

        System.out.println("usuario logeado es: " + user.getUsername());
        Usuario usuario = iuser.findByEmail(user.getUsername());
        //System.out.println(pe.getPersonal().getNombre());
        return "home";

    }
}
