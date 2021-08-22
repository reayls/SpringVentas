/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Deary
 */
@Data
@Entity
@Table(name="clientes")
public class Cliente implements Serializable{
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcliente")
    private long id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido")
    private String apellido;
    
    @Column(name="direccion")
    private String direccion;
    
    @OneToOne(mappedBy = "cliente")
    private Usuario usuario;
}
