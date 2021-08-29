/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Deary
 */
@Data
@Entity
@Table(name="productos")
public class Producto  implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idproducto")
    private long idProducto;
    
    private String nombre;
    
    private double costo;
    
    private double precio;
    
    private int stock;
    
    @JsonIgnore
    @OneToMany(mappedBy = "producto", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<detalle_venta> detventas;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.idProducto ^ (this.idProducto >>> 32));
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
        final Producto other = (Producto) obj;
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", costo=" + costo + ", precio=" + precio + ", stock=" + stock + '}';
    }
      
}
