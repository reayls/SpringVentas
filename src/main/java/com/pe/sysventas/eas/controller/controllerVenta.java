/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pe.sysventas.eas.Entidades.*;
import com.pe.sysventas.eas.Interfaces.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Deary
 */
@Controller
@RequestMapping("/ventas")
public class controllerVenta {
    
    @Autowired
    ICliente icliente;
    @Autowired
    IPersonal ipersonal;
    @Autowired
    IVentas iventa;
    @Autowired
    IUser iuser;
    @Autowired
    IProducto iproducto;
    
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @GetMapping("")
    public String ventas(Model model){
        model.addAttribute("ventas", iventa.findAll());
        return "view-ventas";
    }
    
    @RequestMapping(value="/vents", method=RequestMethod.GET)
    @JsonView(Views.ClientesVentasDetalle.class)
    public ResponseEntity<List<Cliente>> ads(){
        
        return new ResponseEntity<List<Cliente>>(icliente.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/nueva-venta")
    public String venta(Model model, HttpServletRequest request) {
        Venta venta = new Venta();
        double total = 0;
        List<detalle_venta> carrito = this.obtenerCarrito(request);
        for (detalle_venta car: carrito) total += car.getCantidad()*car.getPrecio();
        venta.setFecha(dtf.format(LocalDateTime.now()));
        venta.setTotal(total);
        model.addAttribute("carrito", carrito);
        model.addAttribute("venta", venta);
        model.addAttribute("productos", iproducto.findAll());
        return "new-venta";
    }

    @GetMapping("/carrito")
    public ResponseEntity<List<detalle_venta>> carrito(HttpServletRequest request) {
        
        List<detalle_venta> carrito = this.obtenerCarrito(request);
        Long idproducto = Long.parseLong(request.getParameter("id"));
        int cantid = Integer.parseInt(request.getParameter("cantidad"));
        Producto buscarpro = iproducto.findByIdProducto(idproducto);
        System.out.println("precio :"+buscarpro.getPrecio());
        detalle_venta newdetalle = new detalle_venta();
        newdetalle.setProducto(buscarpro);
        newdetalle.setCosto(buscarpro.getCosto());
        newdetalle.setPrecio(buscarpro.getPrecio());
        newdetalle.setCantidad(cantid);
        newdetalle.setTotalxproducto(cantid * buscarpro.getPrecio());
        
        boolean existenCarrito=false;
        for(detalle_venta d:carrito)
        {
            if(d.getProducto().getIdProducto()==buscarpro.getIdProducto())
            {
                existenCarrito=true;
                break;
            }
        }
        if(!existenCarrito)carrito.add(newdetalle);
        actualizarCarrito(carrito,request);
        return new ResponseEntity<List<detalle_venta>>(carrito, HttpStatus.OK);
    }

    @GetMapping(value = "/quitar")
    public ResponseEntity<List<detalle_venta>> quitarDelCarrito(HttpServletRequest request) {
        int indice=Integer.parseInt(request.getParameter("indice"));
        List<detalle_venta> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.actualizarCarrito(carrito,request);
        }
        return new ResponseEntity<List<detalle_venta>>(carrito, HttpStatus.OK);
    }

    @PostMapping("/generar-venta")
    public String generarventa(Venta venta, @AuthenticationPrincipal User user,HttpServletRequest request) {
        
        List<detalle_venta> carrito = this.obtenerCarrito(request);
        double total=0;
        for (detalle_venta det: carrito){
            det.setProducto(actualizarStock(det.getProducto().getIdProducto(),det.getCantidad()));
            total+=det.getCantidad()*det.getPrecio();
        }
        venta.setFecha(dtf.format(LocalDateTime.now()));
        venta.setTotal(total);
        venta.setEstado("Completado");
        venta.setCliente(icliente.findById(venta.getCliente().getId()).get());
        venta.setPersonal(iuser.findByEmail(user.getUsername()).getPersonal());
        venta.setDetventas(carrito);

        System.out.println(venta.getCliente().getNombre());
        System.out.println(venta.getFecha());
        System.out.println(venta.getPersonal().getNombre());
        System.out.println(venta.getTotal());
        iventa.save(venta);
        carrito.clear();
        actualizarCarrito(carrito,request);
        
        return "redirect:/home";
    }
    private List<detalle_venta> obtenerCarrito(HttpServletRequest request) {
        List<detalle_venta> carrito = (List<detalle_venta>) request.getSession().getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }
        return carrito;
    }

    private void actualizarCarrito(List<detalle_venta> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }
    private Producto actualizarStock(Long id,int cantidad){
        Producto producto= new Producto();
        producto=iproducto.findByIdProducto(id);
        System.out.println("cantidad anterior: "+producto.getStock());
        producto.setStock(producto.getStock()-cantidad);
        System.out.println(" cantidad actual: "+producto.getStock());
        return producto;
    }
}
