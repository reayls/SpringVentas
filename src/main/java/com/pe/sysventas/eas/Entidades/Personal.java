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
@Table(name="personal")
public class Personal implements Serializable{
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idpersonal")
    private long idpersonal;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido")
    private String apellido;
    
    @Column(name="direccion")
    private String direccion;
    
    @OneToOne(mappedBy = "personal")
    @JsonIgnore
    private Usuario usuario;
    
    @ManyToMany(mappedBy = "personal")
    @JsonIgnore
    private Set<Venta> ventas; 
    
}
