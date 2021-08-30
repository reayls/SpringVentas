/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
    private long idDetalle;
    
    private double costo;
    
    private double precio;
    
    private int cantidad;
    
    private double totalxproducto;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_producto", referencedColumnName = "idproducto")
    private Producto producto;
    
    @ManyToOne(fetch= FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="fk_venta", referencedColumnName = "idventa")
    @JsonIgnore
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

    @Override
    public String toString() {
        return "detalle_venta{" + "idDetalle=" + idDetalle + ", costo=" + costo + ", precio=" + precio + ", cantidad=" + cantidad + ", total=" + totalxproducto + '}';
    }
    
    
}
