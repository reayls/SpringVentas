/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.controller;

import com.pe.sysventas.eas.Entidades.Producto;
import com.pe.sysventas.eas.Interfaces.IProducto;
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
@RequestMapping("/productos")
public class controllerProducto {
    
    @Autowired
    IProducto iproducto;
    
    @GetMapping("")
    public String listaProductos(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", iproducto.findAll());
        return "view-productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(Producto producto) {
        iproducto.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{idProducto}")
    public ResponseEntity<Producto> prod(Producto producto) {

        return new ResponseEntity<Producto>(iproducto.findById(producto.getIdProducto()).get(), HttpStatus.OK);
    }
}
