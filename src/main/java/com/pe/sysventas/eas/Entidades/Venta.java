/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Deary
 */
@Data
@Entity
@Table(name="ventas")

public class Venta implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idventa")
    private long idVenta;
    
    private String fecha;
    
    private double total;
    
    private String estado;
    
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="fk_cliente", referencedColumnName = "idcliente")
    private Cliente cliente ;
    
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="fk_personal", referencedColumnName = "idpersonal")
    private Personal personal;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<detalle_venta> detventas;
    
}
