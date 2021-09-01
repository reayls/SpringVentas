/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Deary
 */
@Data
@Entity
@Table(name="detalleventa")
public class detalle_venta implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddetalle")
    @JsonView({Views.ClientesVentasDetalle.class})
    private long idDetalle;
    
    @JsonView({Views.ClientesVentasDetalle.class})
    private double costo;
    
    @JsonView({Views.ClientesVentasDetalle.class})
    private double precio;
    
    @JsonView({Views.ClientesVentasDetalle.class})
    private int cantidad;
    
    @JsonView({Views.ClientesVentasDetalle.class})
    private double totalxproducto;
    
    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_producto", referencedColumnName = "idproducto")
    @JsonView({Views.ClientesVentasDetalle.class})
    private Producto producto;
    
    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_venta", referencedColumnName = "idventa")
    private Venta venta;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (int) (this.idDetalle ^ (this.idDetalle >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final detalle_venta other = (detalle_venta) obj;
        if (this.idDetalle != other.idDetalle) {
            return false;
        }
        return true;
    }
}
