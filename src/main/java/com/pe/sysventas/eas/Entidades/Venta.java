/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView({Views.ClientesVentas.class})
    private long idVenta;
    
    @JsonView({Views.ClientesVentas.class})
    private String fecha;
    
    @JsonView({Views.ClientesVentas.class})
    private double total;
    
    @JsonView({Views.ClientesVentas.class})
    private String estado;
    
    @ManyToOne(fetch= FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name="fk_cliente", referencedColumnName = "idcliente")
    private Cliente cliente ;
    
    @ManyToOne(fetch= FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinColumn(name="fk_personal", referencedColumnName = "idpersonal")
    @JsonView({Views.ClientesVentas.class})
    private Personal personal;
    
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "venta", cascade = CascadeType.ALL)
    @JsonView({Views.ClientesVentasDetalle.class})
    private List<detalle_venta> detventas;

    public void setDetventas(List<detalle_venta> detventas) {
        this.detventas = detventas;
        for(detalle_venta d: detventas){
            d.setVenta(this);
        }
    }
}
